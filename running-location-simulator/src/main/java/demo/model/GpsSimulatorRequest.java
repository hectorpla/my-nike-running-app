package demo.model;

import lombok.Data;

/**
 * Created by hectorlueng on 4/16/18.
 */

@Data
public class GpsSimulatorRequest {
    private String runningId;
    private double speed;
    private boolean move = true;
    private boolean exportPositionsToMessaging = true;
    private Integer reportInterval = 500;
    private int secondsToError = 0;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private String polyline;
    private MedicalInfo medicalInfo;

    public String toString() {
        return  String.format("Request: ID %s", runningId);
    }
}
