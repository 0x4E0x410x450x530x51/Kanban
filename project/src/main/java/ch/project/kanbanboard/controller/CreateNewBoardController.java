package ch.project.kanbanboard.controller;

import ch.project.kanbanboard.entity.Board;
import ch.project.kanbanboard.entity.JSONFILE;
import ch.project.kanbanboard.repository.BoardRepository;
import ch.project.kanbanboard.service.ConsoleMessageManager;
import ch.project.kanbanboard.service.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CreateNewBoardController {

    Board board;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ConsoleMessageManager consoleMessageManager;

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
    @PostMapping(value = "/newBoardLink")
    public @ResponseBody String createNewBoardLink(JSONFILE file) throws UnknownHostException {

        //Check if Authorized
        //Get Default Settings(Evtl HardCoded in JS) + Custom Settings -> Save to DB


        //Generate UUID + Save to Clipboard + Save to DB
        url = "https://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/board/";
        String uuid = String.valueOf(generateUUID.generateUUID());
        url = url + uuid;
        consoleMessageManager.printInfoMessage(url);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(url), null);

        board.setId(uuid);
        board.setCreationDate(LocalDate.now());
        boardRepository.save(board);

        return url;
    }

}
