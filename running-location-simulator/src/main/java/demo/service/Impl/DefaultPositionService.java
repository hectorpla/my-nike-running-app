package demo.service.Impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.model.CurrentPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import demo.service.PositionService;

/**
 * Created by hectorlueng on 5/12/18.
 */

@Service
@Slf4j
public class DefaultPositionService implements PositionService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPositionService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${com.zhpnl.running.location.distribution}")
    private String runningLocationDistribution;

    @HystrixCommand(fallbackMethod = "processPositionFallback")
    @Override
    public void processPositionInfo(long id, CurrentPosition currentPosition, boolean sendPositionToDistributionService) {
        if (sendPositionToDistributionService) {
            log.info(String.format("Thread %d Simulator is calling distribution REST API"),
                    Thread.currentThread().getId());
            this.restTemplate.postForLocation(runningLocationDistribution + "/api/locations",
                    currentPosition);
        }
    }

    public void processPositionFallback(long id, CurrentPosition currentPosition, boolean sendPositionToDistributionService) {
        log.error("Hystrix Fallback: Unable to send message to distribution service");
    }
}
