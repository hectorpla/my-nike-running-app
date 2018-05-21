package demo.service;

import demo.model.GpsSimulatorRequest;
import demo.model.Point;
import demo.task.LocationSimulator;

import java.util.List;

/**
 * Created by hectorlueng on 5/12/18.
 */
public interface GpsSimulatorFactory {

    LocationSimulator prepareGpsSimulator(GpsSimulatorRequest gpsSimulatorRequest);

    LocationSimulator prepareGpsSimulator(LocationSimulator locationSimulator, List<Point> points);
}
