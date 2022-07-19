package ch.project.kanbanboard.repository;

import ch.project.kanbanboard.entity.Constantsetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstantsettingRepository extends JpaRepository<Constantsetting, Integer> {
}