package demo.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hectorlueng on 5/12/18.
 */

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@JsonPropertyOrder({"NumberOfSimulatorRequests", "GpsSimulatorRequests"})
public class SimulatorInitLocations {

    private List<GpsSimulatorRequest> gpsSimulatorRequests = new ArrayList<>(0);

    public int getNumberOfSimulatorRequests() {
        return gpsSimulatorRequests.size();
    }

    public void setGpsSimulatorRequests(List<GpsSimulatorRequest> gpsSimulatorRequests) {
        Assert.notEmpty(gpsSimulatorRequests, "gpsSimulatorRequests cannot be empty");
        this.gpsSimulatorRequests = gpsSimulatorRequests;
    }
}
