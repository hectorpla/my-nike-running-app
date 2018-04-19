package demo.model;

/**
 * Created by hectorlueng on 4/16/18.
 */
public class CurrentPosition {
    private String runningId;
    private Point location;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private double speed;
    private double heading;
    private MedicalInfo medicalInfo;
}
