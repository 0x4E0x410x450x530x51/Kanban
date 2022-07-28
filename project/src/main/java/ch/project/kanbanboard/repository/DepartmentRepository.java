package ch.project.kanbanboard.repository;

import ch.project.kanbanboard.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}