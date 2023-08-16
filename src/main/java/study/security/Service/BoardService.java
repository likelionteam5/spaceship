package study.security.Service;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.security.Entity.BaseEntity;
import study.security.Entity.BoardEntity;
import study.security.Repository.BoardRepository;
import study.security.Repository.UserRepository;
import study.security.dto.BoardDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void savePost(BoardDTO boardDTO, String user) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO, user);
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


    public List<BoardDTO> findByLocation(String location) {
        List<BoardEntity> boardEntities = boardRepository.findByLocation(location);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        if (!boardEntities.isEmpty()) {
            for (BoardEntity boardEntity : boardEntities) {
                boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
            }
            return boardDTOList;
        } else {
            return null;
        }
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
            boardEntity.setUpdatedTime(LocalDateTime.now());
            boardRepository.save(boardEntity);

            // DTO로 변환하여 반환
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }else
            return null;

    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 10;
        // 한 페이지당 10개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록: id, writer, title, location, createdTime
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardTitle(), board.getLocation(), board.getCreatedTime()));
        return boardDTOS;
    }



}
