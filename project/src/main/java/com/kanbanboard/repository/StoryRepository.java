package com.kanbanboard.repository;

import com.kanbanboard.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Integer> {
}