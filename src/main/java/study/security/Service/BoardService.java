package study.security.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.security.Entity.BoardEntity;
import study.security.Repository.BoardRepository;
import study.security.dto.BoardDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void write(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else
            return null;
    }

    public BoardDTO updateBoard(Long id, BoardDTO updatedBoard) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        // 아이디가 존재한다면
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();

            // 업데이트할 제목, 내용이 있다면 갱신
            if (updatedBoard.getBoardTitle() != null){
                boardEntity.setBoardTitle(updatedBoard.getBoardTitle());
            }
            if(updatedBoard.getBoardContents() != null){
                boardEntity.setBoardContents(updatedBoard.getBoardContents());
            }

            // 변경된 내용을 저장
            boardRepository.save(boardEntity);

            // DTO로 변환하여 반환
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }else
            return null;

    }
}
