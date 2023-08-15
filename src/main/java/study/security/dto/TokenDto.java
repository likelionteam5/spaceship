package study.security.dto;

import lombok.*;

//token 정보 response
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;
}