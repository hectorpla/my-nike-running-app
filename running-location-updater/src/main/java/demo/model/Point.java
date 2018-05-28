package demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hectorlueng on 5/21/18.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    private Double longitude;
    private Double latitude;
}
