package ch.project.kanbanboard.controller;

import ch.project.kanbanboard.entity.JSONFILE;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

	//Sending Message to Client - Websocket 🌎




	//New Model + JSON Builder
	@MessageMapping("/board/{fleetId}")
	@SendTo("/topic/board/{fleetId}")
	public JSONFILE simple(@DestinationVariable String fleetId, String text) {
		return new JSONFILE(fleetId, text);
	}
}
