package study.security.Service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import study.security.Entity.Board;
import study.security.Entity.Likey;
import study.security.Entity.User;
import study.security.Repository.BoardRepository;
import study.security.Repository.LikeyRepository;
import study.security.dto.ResponseDto;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeyService {
    private final BoardServiceImpl boardService;
    private final BoardRepository boardRepository;
    private final LikeyRepository likeyRepository;

    public ResponseDto addLike(Long boardId, User user){
        Board board = boardRepository.findById(boardId).get();
        if(!likeyRepository.existsByUserAndBoard(user,board)){
            board.setLikeCount(board.getLikeCount()+1);
            likeyRepository.save(new Likey(user,board));
            return ResponseDto.setSuccess("like success");
        }else{
            board.setLikeCount(board.getLikeCount()-1);
            likeyRepository.deleteByUserAndBoard(user,board);
            return ResponseDto.setFailed("like delete");
        }
    }


}
