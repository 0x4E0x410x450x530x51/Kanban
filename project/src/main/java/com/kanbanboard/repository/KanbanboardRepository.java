package com.kanbanboard.repository;

import com.kanbanboard.model.Kanbanboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanboardRepository extends JpaRepository<Kanbanboard, Integer> {
}