package com.kanbanboard.repository;

import com.kanbanboard.model.UserKanbanboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserKanbanboardRepository<ID> extends JpaRepository<UserKanbanboard, ID> {

    List<UserKanbanboard> findByKanbanboardID_Id(Integer id);

    UserKanbanboard findByUserID_Id(Integer id);



}