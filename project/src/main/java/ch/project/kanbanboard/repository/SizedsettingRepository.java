package ch.project.kanbanboard.repository;

import ch.project.kanbanboard.entity.Sizedsetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizedsettingRepository extends JpaRepository<Sizedsetting, Integer> {
}