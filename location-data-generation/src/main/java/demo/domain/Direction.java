package demo.domain;

import lombok.Data;

/**
 * Created by hectorlueng on 4/22/18.
 */

@Data
public class Direction {
    private String fromAddress;
    private String toAddress;
    private String polyline; // compressed representation?


}
