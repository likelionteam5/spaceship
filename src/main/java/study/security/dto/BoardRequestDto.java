package study.security.dto;

import lombok.*;
import study.security.Entity.Board;
import study.security.Jwt.TokenProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
public final class BoardRequestDto {
//    private String userName;
    private String title;
    private String content;
    private String location;
    private LocalDateTime createDateTime;

    @Builder //객체 생성을 위한 패턴. 필드 순서에 상환 없음.
    public BoardRequestDto(String title, String content, String location, LocalDateTime createDateTime)
    {
        this.title = title;
        this.content = content;
        this.location = location;
        this.createDateTime = createDateTime;
    }
}
