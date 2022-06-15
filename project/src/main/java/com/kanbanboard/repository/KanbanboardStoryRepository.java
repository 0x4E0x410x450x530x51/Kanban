package com.kanbanboard.repository;

import com.kanbanboard.model.KanbanboardStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanboardStoryRepository< ID> extends JpaRepository<KanbanboardStory, ID> {
}