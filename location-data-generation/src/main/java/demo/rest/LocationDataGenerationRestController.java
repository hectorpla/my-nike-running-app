package demo.rest;

import demo.domain.Direction;
import demo.domain.Location;
import demo.service.LocationDataGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hectorlueng on 4/22/18.
 */

@RestController
public class LocationDataGenerationRestController {
    private LocationDataGenerationService locationDataGenerationService;

    @Autowired
    LocationDataGenerationRestController(LocationDataGenerationService locationDataGenerationService) {
        this.locationDataGenerationService = locationDataGenerationService;
    }

    @RequestMapping(path = "/locations", method = RequestMethod.POST)
    public Direction getDirection(@RequestBody Location location) {
        return locationDataGenerationService.getDirection(location);
    }
}
