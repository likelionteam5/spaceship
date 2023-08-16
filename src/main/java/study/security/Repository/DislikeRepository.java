package study.security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.Entity.Board;
import study.security.Entity.Dislike;
import study.security.Entity.Likey;
import study.security.Entity.User;

public interface DislikeRepository extends JpaRepository<Dislike,Integer>{
    boolean existsByUserAndBoard(User user, Board board);
    void deleteByUserAndBoard(User user,Board board);
}
