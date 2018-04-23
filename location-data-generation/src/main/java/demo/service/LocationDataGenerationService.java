package demo.service;

import demo.domain.Direction;
import demo.domain.Location;

/**
 * Created by hectorlueng on 4/22/18.
 */

public interface LocationDataGenerationService {
    Direction getDirection(Location location);
}
