package study.security.dto;

import lombok.*;

//token정보 response
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;
}