package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hectorlueng on 5/27/18.
 */
public class SupplyLocation {

    private String id;
    private String address1;
    private String address2;
    private String city;

    private Point location;
    private String state;
    private String zip;
    private String type;

    @JsonCreator
    public SupplyLocation(@JsonProperty("latitude") double lat,
                          @JsonProperty("longitude") double lon) {
        this.location = new Point(lat, lon);
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }
}
