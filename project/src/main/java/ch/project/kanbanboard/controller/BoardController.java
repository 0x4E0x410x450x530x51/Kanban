package ch.project.kanbanboard.controller;

import ch.project.kanbanboard.entity.Board;
import ch.project.kanbanboard.entity.JSONFILE;
import ch.project.kanbanboard.repository.BoardRepository;
import ch.project.kanbanboard.service.ConsoleMessageManager;
import ch.project.kanbanboard.service.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {

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

    public BoardController() throws UnknownHostException {
    }


    @PostMapping(value = "/newBoard")
    public @ResponseBody String createNewBoard(JSONFILE file, Boolean auth) throws UnknownHostException {

        //Check if Authorized
        //Get Default Settings(Evtl HardCoded in JS) + Custom Settings -> Save to DB

        //********************************************************************
        //Generate UUID + Save to Clipboard + Save to DB

        url = "https://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/board/";
        String uuid = String.valueOf(generateUUID.generateUUID());
        url = url + uuid;
        consoleMessageManager.printInfoMessage(url);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(url), null);

        //Save Board to Board Table on DB
        board.setId(uuid);
        board.setCreationDate(LocalDate.now());
        boardRepository.save(board);

        //********************************************************************
        //Get Rules from JSON + Save to DB

        //********************************************************************
        //Get Departments from JSON + Save to DB

        //...
        return url;
    }

    @PostMapping(value = "/submitBoard")
    public @ResponseBody String submitBoard(JSONFILE file) throws UnknownHostException {

        //Get JSON + Save to DB

        return "Submit";

    }

    @GetMapping(value = "/viewBoard")
    public @ResponseBody String viewBoard(JSONFILE file) throws UnknownHostException {

        //Get JSON + Save to DB

        return "View";

    }
}
