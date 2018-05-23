package demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by hectorlueng on 5/21/18.
 */

// front end <-> back end
// repository
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentPositionDto {
    private String runningId;
    private Point location;
    private Double speed;
    private Double heading;
}


