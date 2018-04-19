package demo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by hectorlueng on 4/16/18.
 */

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MedicalInfo {
    private long medicalInfoId;
    private String bandMake;
    private String medicalInfoClassification;
    private String description;
    private String aidInstructions;

    public MedicalInfo() {

    }
}
