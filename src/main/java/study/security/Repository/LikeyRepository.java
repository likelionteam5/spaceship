package study.security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.Entity.Board;
import study.security.Entity.Likey;
import study.security.Entity.User;

public interface LikeyRepository extends JpaRepository<Likey,Integer>{
    boolean existsByUserAndBoard(User user, Board board);
    //삭제
    void deleteByUserAndBoard(User user,Board board);
}
