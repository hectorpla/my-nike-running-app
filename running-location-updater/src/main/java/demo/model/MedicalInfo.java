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
public class MedicalInfo {

    private String bandMake;
    private String medCode;
    private String medicalInfoId;
    private String medicalClassification;
    private String description;
    private String aidInstructions;
    private String fmi;
    private String bfr;
}
