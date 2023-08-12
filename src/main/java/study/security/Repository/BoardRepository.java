package study.security.Repository;

import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import study.security.Entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
