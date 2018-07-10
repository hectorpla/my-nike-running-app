package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by hectorlueng on 5/12/18.
 */

@EnableBinding(Source.class)
@RestController
@Slf4j
public class RunnerPositionSource {

    @Autowired
    private MessageChannel output;

    @RequestMapping(path = "/api/locations", method = RequestMethod.POST)
    @Output(Source.OUTPUT) // otherwise declare queue named "output"
    public void locations(@RequestBody String positionInfo) {
        log.info("Receiving currentPosition from Simulator: " + positionInfo);
        this.output.send(MessageBuilder.withPayload(positionInfo).build());
    }
}
