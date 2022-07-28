package ch.project.kanbanboard.repository;

import ch.project.kanbanboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, String> {
    @Override
    Optional<Board> findById(String s);
}