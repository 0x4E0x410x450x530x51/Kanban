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

        if (true || boardRepository.existsById(boardKey)) {
            consoleMessageManager.printInfoMessage("Board exists!");
            return new ModelAndView("../html/boardPage.html");
        } else {
            consoleMessageManager.printErrorMessage("Board does not exist!");
            return new ModelAndView("redirect:/");
        }


        //Load Board Settings + Create JSON and Return
    }

    @GetMapping("/createSimulation")
    public ModelAndView createBoard() {
        consoleMessageManager.printInfoMessage("Loading Create Board Page");
        //View Create Simulation Page
        ModelAndView modelAndView = new ModelAndView("html/createSimulationPage.html");
        return modelAndView;
    }


    @GetMapping("/")
    public ModelAndView landingPage() {

        consoleMessageManager.printInfoMessage("Loading Landing Page");
        //View LandingPage
        ModelAndView modelAndView = new ModelAndView("html/landingPage.html");
        return modelAndView;
    }

    @GetMapping("/help")
    public ModelAndView helpPage() {

        consoleMessageManager.printInfoMessage("Loading Landing Page");
        //View HelpPage
        ModelAndView modelAndView = new ModelAndView("html/helpPage.html");
        return modelAndView;
    }
}
