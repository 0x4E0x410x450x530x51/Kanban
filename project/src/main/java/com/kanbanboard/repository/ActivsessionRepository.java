package com.kanbanboard.repository;

import com.kanbanboard.model.Activsession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivsessionRepository extends JpaRepository<Activsession, Integer> {
    Activsession findBySessionID(String sessionID);

    boolean existsBySessionID(String sessionID);



}