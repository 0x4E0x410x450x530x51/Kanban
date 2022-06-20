package com.kanbanboard.controller;


import com.kanbanboard.payload.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.websocket.server.PathParam;
import java.net.http.HttpRequest;


@RestController
@RequestMapping("/page")
public class KanbanController {

    @GetMapping(value = "/board/{id}")
    public String getboard(@CookieValue("JSESSIONID") String jsess, @PathVariable("id") String id){


        return jsess;
    }
}
