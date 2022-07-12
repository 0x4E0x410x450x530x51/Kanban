package ch.websocket.controller;

import ch.websocket.service.GenerateUUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateNewBoardController {

    private String link = "http://172.20.10.3:8080/board/";

    GenerateUUID generateUUID = new GenerateUUID();

    //Building KanbanBoard URL with UUID + DB Check for Duplicate + Save Settings
    @GetMapping("/newBoardLink")
    public String createNewBoardLink() {

        //Get Settings -> Save to DB
        //Return Link

        String uuid = String.valueOf(generateUUID.generateUUID());

        System.out.println(link + uuid);

        return link + uuid;
    }
}
