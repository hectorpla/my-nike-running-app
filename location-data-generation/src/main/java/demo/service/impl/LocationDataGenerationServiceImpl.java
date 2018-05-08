package demo.service.impl;

import demo.domain.Direction;
import demo.domain.Location;
import demo.domain.LocationRepository;
import demo.service.LocationDataGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hectorlueng on 5/5/18.
 */

@Service
public class LocationDataGenerationServiceImpl implements LocationDataGenerationService {

    LocationRepository locationRepository;

    @Autowired
    LocationDataGenerationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;

        // TODO: singleton, how to do DI
//        GeoApiContext context = new GeoApiContext.Builder()
//                .apiKey("AIza...")
//                .build();
}

//    @Bean
//    RestTemplate restTemplate(RestTemplateBuilder ) {
//
//    }

    @Override
    public Direction getDirection(Location location) {
        String fromAddress = location.getFromAddress();
        String toAddress = location.getToAddress();


        // TODO: make unique
        locationRepository.save(location);


        // API
        String apiUrl = "https://maps.googleapis.com/maps/api/directions/json";
        String url = String.format("%s?origin=%s&destination=%s",
                apiUrl, fromAddress, toAddress);

        url = url.replaceAll(" ", "%20");

        try {
            System.out.println(url);

            RestTemplate restTemplate = new RestTemplate();
            HashMap direction =
                    restTemplate.getForObject(url, HashMap.class);

//            System.out.println(polyLineDirection);
            ArrayList routes = (ArrayList) direction.get("routes");
            HashMap route = (HashMap) routes.get(0);
            String polyline = (String) ((HashMap) route.get("overview_polyline")).get("points");
            System.out.println(polyline);

            return new Direction(fromAddress, toAddress, polyline);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
