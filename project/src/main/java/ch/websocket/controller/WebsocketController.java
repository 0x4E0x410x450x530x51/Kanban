package ch.websocket.controller;

import ch.websocket.entity.Simple;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

	@MessageMapping("/board/{fleetId}")
	@SendTo("/topic/board/{fleetId}")
	public Simple simple(@DestinationVariable String fleetId, String text) {
		return new Simple(fleetId, text);
	}
}
