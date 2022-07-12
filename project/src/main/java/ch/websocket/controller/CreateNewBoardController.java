package ch.websocket.controller;

import ch.websocket.service.GenerateUUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateNewBoardController {

    private String link = "http://172.20.10.3:8080/board/";



    GenerateUUID generateUUID = new GenerateUUID();

    @GetMapping("/newBoardLink")
    public String createNewBoardLink() {

        String uuid = String.valueOf(generateUUID.generateUUID());

        System.out.println(link + uuid);

        return link + uuid;
    }
}
