package study.security.dto;

import lombok.*;
import study.security.Entity.Board;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private String region;

    @Builder //객체 생성을 위한 패턴. 필드 순서에 상환 없음.
    public BoardRequestDto(String writer, String title, String content, String retgion)
    {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.region = region;
    }

    public Board toEntity() {
        return Board.builder()
                .writer(this.writer)
                .title(this.title)
                .content(this.content)
                .region(this.region)
                .build();
    }

}
