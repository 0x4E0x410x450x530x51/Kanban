package ch.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @GetMapping("/board/{boardKey}")
    public ModelAndView dynamicPage() {

        //Check if BoardKey is in DB

        //Load Default Settings

        ModelAndView modelAndView = new ModelAndView("../index.html");
        return modelAndView;
    }


}
