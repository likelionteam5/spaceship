package study.security.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.Entity.BoardEntity;
import study.security.Repository.BoardRepository;
import study.security.dto.BoardDTO;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void write(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
