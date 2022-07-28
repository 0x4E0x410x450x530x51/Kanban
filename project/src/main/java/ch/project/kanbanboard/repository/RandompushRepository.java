package ch.project.kanbanboard.repository;

import ch.project.kanbanboard.entity.Randompush;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RandompushRepository extends JpaRepository<Randompush, Integer> {
}