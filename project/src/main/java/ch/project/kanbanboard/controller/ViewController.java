package ch.project.kanbanboard.controller;

import ch.project.kanbanboard.repository.BoardRepository;
import ch.project.kanbanboard.service.ConsoleMessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ConsoleMessageManager consoleMessageManager;

    @GetMapping("/board/{boardKey}")
    public ModelAndView dynamicPage(@PathVariable String boardKey) {

        consoleMessageManager.printInfoMessage("Loading Board");

        if (boardRepository.existsById(boardKey)) {
            consoleMessageManager.printInfoMessage("Board exists!");
            return new ModelAndView("../index.html");
        } else {
            consoleMessageManager.printErrorMessage("Board does not exist!");
            return new ModelAndView("html/dashboard_index.html");
        }

        //Load Board Settings + Create JSON and Return
    }

    @GetMapping("/")
    public ModelAndView landingPage() {

        consoleMessageManager.printInfoMessage("Loading Landing Page");
        //New LandingPage
        ModelAndView modelAndView = new ModelAndView("html/dashboard_index.html");
        return modelAndView;
    }


}
