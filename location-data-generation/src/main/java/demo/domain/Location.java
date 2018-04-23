package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by hectorlueng on 4/22/18.
 */

@JsonInclude(JsonInclude.Include.ALWAYS)
@Data
@Document
public class Location {

    @Id
    private String id;

    private String fromAddress;
    private String toAddress;


}
