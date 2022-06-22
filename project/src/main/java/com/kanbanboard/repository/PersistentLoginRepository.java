package com.kanbanboard.repository;

import com.kanbanboard.model.PersistentLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PersistentLoginRepository extends JpaRepository<PersistentLogin, Integer> {
    PersistentLogin findByUserID(String userID);
    PersistentLogin findBySessionID(String sessionID);

    boolean existsByUserID(String userID);

    boolean existsBySessionID(String sessionID);


    @Override
    void deleteById(Integer integer);
}