package study.security.dto;

import lombok.*;
import study.security.Entity.Board;
import study.security.Jwt.TokenProvider;

@Getter
@Setter
@Data
@NoArgsConstructor
public final class BoardRequestDto {
//    private String userName;
    private String title;
    private String content;
    private String location;

    @Builder //객체 생성을 위한 패턴. 필드 순서에 상환 없음.
    public BoardRequestDto(String title, String content, String location, TokenProvider tokenProvider)
    {
        this.title = title;
        this.content = content;
        this.location = location ;

    }

}
