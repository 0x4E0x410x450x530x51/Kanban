package ch.project.kanbanboard.repository;

import ch.project.kanbanboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {


    @Override
    boolean existsById(String s);
}