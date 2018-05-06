package demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by hectorlueng on 5/5/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PolyLineDirection {

    @Field("overview_polyline")
    private OverviewPolyLine encodedPolyline;

    public String getPolyLine() {
        return encodedPolyline.getPoints();
    }
}
