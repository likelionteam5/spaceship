package study.security.Service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.Entity.Board;
import study.security.Entity.Dislike;
import study.security.Entity.Likey;
import study.security.Entity.User;
import study.security.Repository.BoardRepository;
import study.security.Repository.DislikeRepository;
import study.security.Repository.LikeyRepository;
import study.security.dto.ResponseDto;

@Service
@RequiredArgsConstructor
@Transactional
public class DislikeService {
    private final BoardServiceImpl boardService;
    private final BoardRepository boardRepository;
    private final DislikeRepository dislikeRepository;

    public ResponseDto addDislike(Long boardId, User user){
        Board board = boardRepository.findById(boardId).get();
        if(!dislikeRepository.existsByUserAndBoard(user,board)){
            board.setDislikeCount(board.getDislikeCount()+1);
            dislikeRepository.save(new Dislike(user,board));
            return ResponseDto.setSuccess("like success");
        }else{
            board.setDislikeCount(board.getDislikeCount()-1);
            dislikeRepository.deleteByUserAndBoard(user,board);
            return ResponseDto.setFailed("like delete");
        }
    }


}
