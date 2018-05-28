package demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hectorlueng on 5/27/18.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentPosition {
    private String runningId;
    private Point location;
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private Double speed;
    private Double heading;

    private MedicalInfo medicalInfo;
    private SupplyLocation supplyLocation;
}
