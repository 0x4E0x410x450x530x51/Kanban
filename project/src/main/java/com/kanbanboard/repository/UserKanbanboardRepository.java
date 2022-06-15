package com.kanbanboard.repository;

import com.kanbanboard.model.UserKanbanboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserKanbanboardRepository<ID> extends JpaRepository<UserKanbanboard, ID> {
}