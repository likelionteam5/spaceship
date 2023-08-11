package study.security.dto;


import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

    private String writer;

    private String title;

    private String content;

    private String region;
}
