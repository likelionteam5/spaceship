package study.security.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

    private String userName;

    private String title;

    private String content;

    private String location;
}
