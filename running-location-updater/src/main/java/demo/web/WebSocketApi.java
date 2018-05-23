package demo.web;

import demo.model.CurrentPositionDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * Created by hectorlueng on 5/21/18.
 */

public class WebSocketApi {

    @MessageMapping("/sendMessage")
    @SendTo("topic/locations")
    public CurrentPositionDto sendMessage(CurrentPositionDto message) {
        return message;
    }
}
