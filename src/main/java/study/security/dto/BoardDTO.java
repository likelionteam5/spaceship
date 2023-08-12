package study.security.dto;


import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
//    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private String boardLocal;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

}
