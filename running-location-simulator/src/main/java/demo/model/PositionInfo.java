package demo.model;

import lombok.Data;

/**
 * Created by hectorlueng on 4/16/18.
 */

@Data
public class PositionInfo {
    private String runningId;
    private Point position;
    private RunnerStatus runnerStatus;

    private Leg leg;

    private double distanceFromStart;
    private double speed;

    public PositionInfo() {}
}
