package ch.project.kanbanboard.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ConsoleMessageManager implements ApplicationListener<ApplicationReadyEvent> {

    ConsoleColors consoleColors;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    String space = "   ---   ";
    
    String timePrefix = "\n" + dtf.format(now);
    
    public void printErrorMessage(String message) {
        now = LocalDateTime.now();
        timePrefix = "\n" + dtf.format(now);
        System.out.println(timePrefix + space + consoleColors.ANSI_RED + "ERROR" + consoleColors.ANSI_RESET + space + consoleColors.ANSI_BLUE + message + "\n" + consoleColors.ANSI_RESET);
    }
    
    public void printInfoMessage(String message) {
        now = LocalDateTime.now();
        timePrefix = "\n" + dtf.format(now);
        System.out.println(timePrefix + space + consoleColors.ANSI_GREEN + "INFO" + consoleColors.ANSI_RESET + space + consoleColors.ANSI_BLUE + message + "\n" +consoleColors.ANSI_RESET);

    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        now = LocalDateTime.now();
        timePrefix = "\n" + dtf.format(now);
        System.out.println("\n\n" + timePrefix + space + consoleColors.ANSI_YELLOW + "SYSTEM" + consoleColors.ANSI_RESET+ space + consoleColors.ANSI_BLUE + "Application is ready to serve requests.\n" + consoleColors.ANSI_RESET);
        return;
    }
    
}
