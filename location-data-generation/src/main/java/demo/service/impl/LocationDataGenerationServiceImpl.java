package demo.service.impl;

import com.google.maps.GeoApiContext;
import demo.domain.Direction;
import demo.domain.Location;
import demo.domain.LocationRepository;
import demo.domain.PolyLineDirection;
import demo.service.LocationDataGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

//        try {
//            url = URLEncoder.encode(url, "UTF-8");
            System.out.print(url);

            RestTemplate restTemplate = new RestTemplate();
            PolyLineDirection polyLineDirection =
                    restTemplate.getForObject(url, PolyLineDirection.class);

            

            return new Direction(fromAddress, toAddress, polyLineDirection.getPolyLine());
//        } catch (UnsupportedEncodingException e) {
//            return null;
//        }
    }
}
