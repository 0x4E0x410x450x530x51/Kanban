package ch.project.kanbanboard.repository;

import ch.project.kanbanboard.entity.Normalsetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NormalsettingRepository extends JpaRepository<Normalsetting, Integer> {
}