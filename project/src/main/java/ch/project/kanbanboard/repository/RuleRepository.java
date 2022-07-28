package ch.project.kanbanboard.repository;

import ch.project.kanbanboard.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Integer> {
}