package demo.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Future;

/**
 * Created by hectorlueng on 5/12/18.
 */

@Data
@AllArgsConstructor
public class LocationSimulatorInstance {

    private long instanceId;
    private LocationSimulator locationSimulator;
    private Future<?> locationSimulatorTask;

}
