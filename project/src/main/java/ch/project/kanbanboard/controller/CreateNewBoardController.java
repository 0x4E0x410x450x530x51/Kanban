package ch.project.kanbanboard.controller;

import ch.project.kanbanboard.service.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class CreateNewBoardController {

    @Autowired
    Environment environment;

    @Autowired
    GenerateUUID generateUUID;

    @Value("${server.port}")
    int port;

    private String url;

    public CreateNewBoardController() throws UnknownHostException {
    }


    //Building KanbanBoard URL with UUID + DB Check for Duplicate + Save Settings
    @GetMapping("/newBoardLink")
    public RedirectView createNewBoardLink() throws UnknownHostException {

        //Check if Authorized
        //Get Default Settings + Custom Settings -> Save to DB - Mache Mapping Post
        //Return Link + Redirect to Link
        url = "https://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/board/";
        String uuid = String.valueOf(generateUUID.generateUUID());
        System.out.println(port);
        System.out.println(url);
        url = url + uuid;
        System.out.println(url + uuid);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(url), null);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }

}
