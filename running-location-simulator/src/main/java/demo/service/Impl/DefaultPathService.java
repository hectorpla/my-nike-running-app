package demo.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.model.SimulatorInitLocations;
import demo.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hectorlueng on 5/12/18.
 */

@Service
public class DefaultPathService implements PathService {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public SimulatorInitLocations loadSimulatorInitLocations() {
        final InputStream is = this.getClass().getResourceAsStream("/init-locations.json");

        try {
            return objectMapper.readValue(is, SimulatorInitLocations.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
