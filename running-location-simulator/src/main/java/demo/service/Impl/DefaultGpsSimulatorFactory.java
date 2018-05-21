package demo.service.Impl;

import demo.model.GpsSimulatorRequest;
import demo.model.Leg;
import demo.model.Point;
import demo.support.NavUtils;
import demo.task.LocationSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.service.GpsSimulatorFactory;
import demo.service.PositionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hectorlueng on 5/12/18.
 */

@Service
public class DefaultGpsSimulatorFactory implements GpsSimulatorFactory {

    @Autowired
    private PositionService positionService;

    private final AtomicLong instanceCounter = new AtomicLong();

    @Override
    public LocationSimulator prepareGpsSimulator(GpsSimulatorRequest gpsSimulatorRequest) {
        final LocationSimulator locationSimulator = new LocationSimulator(gpsSimulatorRequest);

        locationSimulator.setPositionService(positionService);
        locationSimulator.setId(this.instanceCounter.incrementAndGet());

        final List<Point> points = NavUtils.decodePolyline(gpsSimulatorRequest.getPolyline());
        locationSimulator.setStartPosition(); // points.iterator().next()

        return prepareGpsSimulator(locationSimulator, points);
    }

    @Override
    public LocationSimulator prepareGpsSimulator(LocationSimulator locationSimulator, List<Point> points) {
        locationSimulator.setCurrentPosition(null);

        final List<Leg> legs = createLegs(points);

        locationSimulator.setLegs(legs);
        locationSimulator.setStartPosition();
        return locationSimulator;
    }

    private List<Leg> createLegs(List<Point> points) {
        final List<Leg> legs = new ArrayList<>();

        for (int i = 0; i < points.size() - 1; i++) {
            Leg leg = new Leg();
            leg.setId(i);

            Point start = points.get(i);
            Point end = points.get(i + 1);
            leg.setStartPosition(start);
            leg.setEndPosition(end);

            Double length = NavUtils.getDistance(start, end);
            leg.setLength(length);
            Double heading = NavUtils.getBearing(start, end);
            leg.setHeading(heading);
            legs.add(leg);
        }

        return legs;
    }
}
