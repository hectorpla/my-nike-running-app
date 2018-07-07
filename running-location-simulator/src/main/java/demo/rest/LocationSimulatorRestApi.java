package demo.rest;

import demo.model.GpsSimulatorRequest;
import demo.model.SimulatorInitLocations;
import demo.service.PathService;
import demo.task.LocationSimulator;
import demo.task.LocationSimulatorInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import demo.service.GpsSimulatorFactory;

import java.util.*;
import java.util.concurrent.Future;

/**
 * Created by hectorlueng on 5/12/18.
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class LocationSimulatorRestApi {

    @Autowired
    private GpsSimulatorFactory gpsSimulatorFactory;

    @Autowired
    private AsyncTaskExecutor taskExecutor;

    @Autowired
    private PathService pathService;

    private Map<Long, LocationSimulatorInstance> taskFutures = new HashMap<>();

    @RequestMapping("/simulation")
    public List<LocationSimulatorInstance> simulation() {
        final SimulatorInitLocations fixture = this.pathService.loadSimulatorInitLocations();
        log.debug("++++simulation request+++++");
        log.debug(fixture.toString());

        final List<LocationSimulatorInstance> instances = new ArrayList<>();

        for (GpsSimulatorRequest gpsSimulatorRequest : fixture.getGpsSimulatorRequests()) {
            System.out.println(gpsSimulatorRequest.getPolyline());
            final LocationSimulator locationSimulator =
                    gpsSimulatorFactory.prepareGpsSimulator(gpsSimulatorRequest);

            final Future<?> future = taskExecutor.submit(locationSimulator);
            final LocationSimulatorInstance instance = new LocationSimulatorInstance(
                    locationSimulator.getId(), locationSimulator, future);
            instances.add(instance);
            taskFutures.put(instance.getInstanceId(), instance);
        }

        return instances;
    }

    @RequestMapping("/cancel")
    public String cancel() {
        int numberOfCancelledTasks = 0;
        int size = taskFutures.size();
        List<Long> toRemove = new LinkedList<>();

        for (Map.Entry<Long, LocationSimulatorInstance> entry : taskFutures.entrySet()) {
            LocationSimulatorInstance instance = entry.getValue();
            instance.getLocationSimulator().cancel();
            boolean wasCancelled = instance.getLocationSimulatorTask().cancel(true); // TODO: true？
            // TODO: bug, not canceled
            if (wasCancelled) {
                log.info(String.format("Thread %d canceled", Thread.currentThread().getId()));
                numberOfCancelledTasks++;
                toRemove.add(entry.getKey());
            }
        }

        // remove the canceled instances from the map
        for (Long key: toRemove) {
            taskFutures.remove(key);
        }

        return "number of task canceled: " + numberOfCancelledTasks + ", left: " +
                (size - numberOfCancelledTasks);
    }
}
