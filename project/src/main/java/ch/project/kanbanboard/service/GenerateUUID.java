package ch.project.kanbanboard.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateUUID {


    /**
     * Generate Random UUID for KanbanBoard URL
     *
     * @return UUID
     */
    public UUID generateUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        return uuid;
    }

}
