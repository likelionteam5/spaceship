package study.security.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.security.dto.BoardDTO;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column(length = 30, nullable = false)
    private String boardTitle;

    @Column(length = 500, nullable = false)
    private String boardContents;

    @Column(length = 15, nullable = false)
    private String location;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setLocation(boardDTO.getLocation());

        return boardEntity;
    }

}
