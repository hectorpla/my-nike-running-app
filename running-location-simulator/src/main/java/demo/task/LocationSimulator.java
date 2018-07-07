package demo.task;

import demo.model.*;
import demo.support.NavUtils;
import lombok.Getter;
import lombok.Setter;
import demo.service.PositionService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hectorlueng on 4/16/18.
 */

@Slf4j
public class LocationSimulator implements Runnable {

    @Setter
    private PositionService positionService;

    @Getter
    @Setter
    private long id;

    private AtomicBoolean cancel = new AtomicBoolean();

    private double speedInMps;

    private boolean shouldMove;
    private boolean exportPositionsToMessaging = true;
    private Integer reportInterval = 500;

    @Getter
    @Setter
    private PositionInfo currentPosition = null;

    @Setter
    private List<Leg> legs;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private String runningId;

    @Setter
    private Point startPoint;
    private Date executionStartTime;

    private MedicalInfo medicalInfo;

    public LocationSimulator(GpsSimulatorRequest gpsSimulatorRequest) {
        this.shouldMove = gpsSimulatorRequest.isMove();
        this.exportPositionsToMessaging = gpsSimulatorRequest.isExportPositionsToMessaging();
        this.setSpeed(gpsSimulatorRequest.getSpeed());
        this.reportInterval = gpsSimulatorRequest.getReportInterval();

        this.runningId = gpsSimulatorRequest.getRunningId();
        this.runnerStatus = gpsSimulatorRequest.getRunnerStatus();
        this.medicalInfo = gpsSimulatorRequest.getMedicalInfo();

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
               log.info(String.format("%d run: ", Thread.currentThread().getId()) + this.currentPosition.toString());
               long startTime = new Date().getTime();

               if (shouldMove) {
                    moveRunningLocation();
                    currentPosition.setSpeed(speedInMps);
               } else {
                   currentPosition.setSpeed(0.0);
               }

               // TODO: implement this state change logic
//               if (this.secondsToError > 0 && startTime - executionStartTime
//                       .getTime() >= this.secondsToError * 1000) {
//                   this.runnerStatus = RunnerStatus.SUPPLY_NOW;
//               }

               currentPosition.setRunnerStatus(runnerStatus);

               final MedicalInfo medicalInfoToUse;

               switch (runnerStatus) {
                   case STOP_NOW:
                   case SUPPLY_SOON:
                   case SUPPLY_NOW:
                       medicalInfoToUse = medicalInfo;
                       break;
                   default:
                       medicalInfoToUse = null;
                       break;
               }

               final CurrentPosition currentPosition = new CurrentPosition(
                       this.currentPosition.getRunningId(),
                       new Point(
                               this.currentPosition.getPosition().getLatitude(),
                               this.currentPosition.getPosition().getLongitude()
                       ),
                       this.currentPosition.getRunnerStatus(),
                       this.currentPosition.getSpeed(),
                       this.currentPosition.getLeg().getHeading(),
                       medicalInfoToUse
               );

               // send current position to distribution demo.service vis REST API
               // implement PositionInfo demo.service
               positionService.processPositionInfo(id, currentPosition,
                       this.exportPositionsToMessaging);

               this.sleep(startTime);
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

    void sleep(long startTime) throws InterruptedException {
        long endTime = new Date().getTime();
        long elapsedTime = endTime - startTime;
        long sleepTime = reportInterval - elapsedTime > 0 ? reportInterval : 0;
        Thread.sleep(sleepTime);
    }

    private void moveRunningLocation() {
        double distance = speedInMps * reportInterval / 1000.0;
        double distanceFromStart = currentPosition.getDistanceFromStart() + distance;
        double excess = 0.0;

        for (int i = currentPosition.getLeg().getId(); i < legs.size(); i++) {
            Leg currentLeg = legs.get(i);
            excess = distanceFromStart > currentLeg.getLength() ? distanceFromStart - currentLeg.getLength() : 0.0;

            if (Double.doubleToRawLongBits(excess) == 0) {
                currentPosition.setDistanceFromStart(distanceFromStart);
                currentPosition.setLeg(currentLeg);

                Point newPosition = NavUtils.getPosition(
                        currentLeg.getStartPosition(),
                        distanceFromStart,
                        currentLeg.getHeading()
                );

                currentPosition.setPosition(newPosition);
                return;
            }
            distanceFromStart = excess;
        }

        // start over
        setStartPosition();
    }

    // Position running location at start of path
    public void setStartPosition() {
        currentPosition = new PositionInfo();
        currentPosition.setRunningId(runningId);
        Leg leg = legs.get(0);
        currentPosition.setLeg(leg);
        currentPosition.setPosition(leg.getStartPosition());
        currentPosition.setDistanceFromStart(0.0);
    }

    public void setSpeed(double speed) {
        this.speedInMps = speed;
    }

    public double getSpeed() { return this.speedInMps; }

    public void cancel() { // TODO: why synchronized
        // TODO all cancel calls are made by the same thread
        log.debug(String.format("Task canceled by thread %d: cancel() called", Thread.currentThread().getId()));
        cancel.set(true);
    }
}
