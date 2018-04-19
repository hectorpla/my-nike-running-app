package demo.task;

import sun.jvm.hotspot.runtime.Thread;

import javax.swing.text.Position;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hectorlueng on 4/16/18.
 */
public class LocationSimulator implements Runnable {
    private long id;

    private AtomicBoolean cancel = new AtomicBoolean();

    private double speedInMps;

    private boolean shouldMove;
    private boolean exportPositionsToMessaging = true;
    private Integer reportInterval = 500;

    private PositionInfo positionInfo;

    private List<Leg> legs;
    private RunerStatus runnerStatus = RunnerStatus.NONE;

    private Position startPoint;
    private Date executionStartTime;

    private MedicalInfo medicalInfo;

    private PositionInfo currentPosition;

    public LocationSimulator(GpsSimulatorRequest gpsSimulatorRequest) {
        this.shouldMove = gpsSimulatorRequest.isMove();
        this.exportPositionsToMessaging = gpsSimulatorRequest.is
    }

    public void setSpeed(double speed) {
        this.speedInMps = speed;
    }

    @Override
    public void run() {
        try {
           executionStartTime = new Date();
           if (cancel.get()) {
               destroy();
               return;
           }

           while (!Thread.interrupted()) {
               long startTime = new Date().getTime();
               sleep(startTime);
           }
        } catch (InterruptedException e) {
            destroy();
            return;
        }
        destroy();
    }

    void destroy() {
        currentPosition = null;
    }

    void sleep(long startTime) {
        long endTime = new Date().getTime();
        long elapsedTime = endTime - startTime;
        long sleepTime = reportInterval - elapsedTime > 0 ? reportInterval : 0;
        Thread.sleep(sleepTime);
    }

    private void moveRunningLocation() {
        double distance = speedInMps * reportInterval / 1000.0;
        double distanceFromStart = currentPosition.getDistanceFromStart() + distance;
        double excess = 0.0;

        for (int i = currentPosition.getLeg().geId(); i < legs.size(); i++) {
            Leg currentLeg = legs.get(i);
            excess = distance > currentLeg.getLength() ? distanceFromStart - currentLeg.getLength() : 0.0;

            if (Double.doubleToRawLongBits(excess) == 0) {
                curentPosition.setDistanceFrom(distanceFromStart);
                currentPostion.setLeg(currentLeg);
                Point
            }
            distanceFromStart = excess;
        }

        setStartPosition();
    }

    // Position running location at start of path
    public void setStartPosition() {
        currentPosition = new PositionInfo();
        currentPosition.
    }
}
