package ch.websocket.websocket.controller;

import ch.websocket.websocket.entity.Simple;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebsocketController {

	SimpMessagingTemplate simpMessagingTemplate;


	/*@MessageMapping("/app/{institutionId}")
	@SendTo("/SendTo/{sendID}")
	public Greeting greeting(@DestinationVariable String institutionId, HelloMessage message) throws Exception {
		System.out.println(institutionId);
		Thread.sleep(500); // simulated delay
		System.out.println(message.getName());
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}*/

	@GetMapping("/board/{boardKey}")
	public ModelAndView dynamicPage() {
		ModelAndView modelAndView = new ModelAndView("../index.html");
		return modelAndView;
	}

	/*@MessageMapping("/fleet/{fleetId}/driver/{driverId}")
	public void simple(@DestinationVariable String fleetId, @DestinationVariable String driverId) {
		simpMessagingTemplate.convertAndSend("/topic/fleet/" + fleetId, new Simple(fleetId, driverId));
	}

	@SubscribeMapping("/fleet/{fleetId}/driver/{driverId}")
	public Simple simple(@DestinationVariable String fleetId, @DestinationVariable String driverId) {
		return new Simple(fleetId, driverId);
	}*/

	@MessageMapping("/fleet/{fleetId}")
	@SendTo("/topic/fleet/{fleetId}")
	public Simple simple(@DestinationVariable String fleetId, String text) {
		return new Simple(fleetId, text);
	}
}
