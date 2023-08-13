package study.security.Repository;

import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.security.Entity.BoardEntity;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Optional<BoardEntity> findByLocation(String location);
    // update board_table ser board_hits=board_hits+1 where id=?
//    @Modifying
//    @Query(value = "update BoardEntity b set b.location=b.location+1 where  b.id=:id")
//    void updateHits(@Param("id") Long id);
}
