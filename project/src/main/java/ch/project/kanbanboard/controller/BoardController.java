package ch.project.kanbanboard.controller;

import ch.project.kanbanboard.entity.*;
import ch.project.kanbanboard.repository.BoardRepository;
import ch.project.kanbanboard.repository.SettingsconfigurationRepository;
import ch.project.kanbanboard.service.ConsoleMessageManager;
import ch.project.kanbanboard.service.GenerateUUID;
import org.json.JSONObject;
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

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {

    Board board;

    @Autowired
    SettingsconfigurationRepository settingsconfigurationRepository;

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

    /*{
    "rules":[
        {
            "ID":1,
            "maxWipPerPerson":10,
            "maxPeopleWorkOnWip":10,
            "warmupTime":1.5
        }
    ],
    "constantSettings":[
        {
            "ID":1,
            "amountHours":1.5,
            "departmentID":1,
            "desc":"Hello World"
        }
    ],
    "scrumcreate":[
        {
            "ID":1,
            "iteration_len":5,
            "taskPerIteration":5,
            "wipSub":true,
            "desc":"Hello World"
        }
    ],
    "SettingsConfiguration":[
        {
            "ID":1,
            "boardUUID":"Hello World",
            "constantID":1,
            "normalID":1,
            "sizedID":1,
            "rulesID":1,
            "defaultValue":true,
            "name":"Hello World!"
        }
    ],
    "createStrat":[
        {
            "ID":1,
            "constantpushID":1,
            "randompushID":1,
            "scrumcreateID":1
        }
    ],
    "board":[
        {
            "UUID":"Hello World",
            "creationDate":"2022-05-16"
        }
    ],
    "constantpush":[
        {
            "ID":1,
            "itemsperday":1.5,
            "desc":"Hello World"
        }
    ],
    "normalSettings":[
        {
            "ID":1,
            "meanHours":1.1,
            "variations":1.5,
            "departmentID":1,
            "desc":"Hello World"
        }
    ],
    "randompush":[
        {
            "ID":1,
            "demandLevel":1.5,
            "batchsize":1,
            "desc":"Hello World"
        }
    ],
    "departmentSized":[
        {
            "ID":1,
            "sizedSettings":1,
            "effort":1,
            "departmentID":1
        }
    ],
    "sizedSettings":[
        {
            "ID":1,
            "effortSmall":1.5,
            "probabilitySmall":1,
            "effortMedium":1.5,
            "probabilityMedium":1,
            "effortLarge":1.5,
            "probabilityLarge":1,
            "effortXLarge":1.5,
            "probabilityXLarge":1,
            "desc":"Hello World"
        }
    ],
    "departments": [
        {
            "ID":1,
            "settingsID":1,
            "departmentName":"Hello World",
            "members":1,
            "colIndex":1,
            "efficiency":1,
            "doingLimit":5,
            "doneLimit":1
        }
    ]
}
*/

    Rule rule;
    Constantsetting constantsetting;
    Scrumcreate scrumcreate;
    Settingsconfiguration settingsconfiguration;
    Createstrat createstrat;
    Constantpush constantpush;

    @PostMapping(value = "/newBoard")
    public @ResponseBody String createNewBoard(String JsonData, Boolean auth) throws UnknownHostException {

        JSONObject obj = new JSONObject(JsonData);

        //********************************************************************
        //Generate UUID + Save to Clipboard + Save to DB

        url = "https://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/board/";
        String uuid = String.valueOf(generateUUID.generateUUID());
        url = url + uuid;
        consoleMessageManager.printInfoMessage(url);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(url), null);

        //********************************************************************
        //Get JSON Data



        //Check if Authorized
        //Get Default Settings(Evtl HardCoded in JS) + Custom Settings -> Save to DB



        //Save Board to Board Table on DB

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

    @GetMapping(value = "/getBoardData")
    public @ResponseBody String viewBoard(String UUID, JSONFILE file) throws UnknownHostException {

        //Get Data from DB + Create JSON + Return

        //Settingsconfiguration settingsconfiguration = settingsconfigurationRepository.findByBoardUUID_IdAndDefaultValue(UUID, true);

        String jsonString = new JSONObject()
                .put("JSON1", "Hello World!")
                .put("JSON2", "Hello my World!")
                .put("JSON3", new JSONObject().put("key1", "value1"))
                .toString();

        System.out.println(jsonString);

        return jsonString;

    }
}
