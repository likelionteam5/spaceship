package study.security.dto;


import lombok.*;
import study.security.Entity.BoardEntity;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String location;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setLocation(boardEntity.getLocation());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return boardDTO;
    }

    public BoardDTO(Long id, String boardWriter, String boardTitle, String location, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.location = location;
        this.boardCreatedTime = boardCreatedTime;
    }
}
