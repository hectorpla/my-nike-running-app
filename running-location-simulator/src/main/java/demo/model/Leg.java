package demo.model;

import lombok.Data;

/**
 * Created by hectorlueng on 4/16/18.
 */

@Data
public class Leg {
    private int id;
    private Point startPosition;
    private Point endPosition;
    private double length;
    private double heading;

    public Leg() {

    }
}
