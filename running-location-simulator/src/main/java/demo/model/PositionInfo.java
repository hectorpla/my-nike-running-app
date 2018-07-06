package demo.model;

import lombok.Data;

/**
 * Created by hectorlueng on 4/16/18.
 */

@Data
public class PositionInfo {
    private String runningId;
    private Point position;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;

    private Leg leg;

    private double distanceFromStart;
    private double speed;

    public PositionInfo() {}

    public String statusToString(RunnerStatus status) {
        switch (status) {
            case SUPPLY_NOW:
                return "SUPPLY_NOW";
            case SUPPLY_SOON:
                return "SUPPLY_SOON";
            case STOP_NOW:
                return "STOP_NOW";
            default:
                return "None";
        }
    }

    public String toString() {
        return String.format("PositionInfo: at Point %s: status %s, speed %f, distance from start: %f", position,
                statusToString(runnerStatus), speed, distanceFromStart);
    }
}
