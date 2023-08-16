package study.security.dto;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import study.security.Entity.BoardEntity;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id; // 게시판 번호
    private String userName;
    private String boardTitle;
    private String boardContents;
    private String location;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setLocation(boardEntity.getLocation());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return boardDTO;
    }

    public BoardDTO(Long id, String boardTitle, String location, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.location = location;
        this.boardCreatedTime = boardCreatedTime;
    }
}
