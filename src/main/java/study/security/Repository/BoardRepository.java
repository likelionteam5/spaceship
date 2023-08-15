package study.security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.Entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByTitleContaining(String keyword);
    void deleteById(Long id);
    List<Board> findByLocation(String location);


    List<Board> findAllByOrderByIdDesc();
}