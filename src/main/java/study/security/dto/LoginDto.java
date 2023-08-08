package study.security.dto;

import jakarta.persistence.Column;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Column(length = 25)
    private String username;

    @NotNull
    @Column(length = 40)
    private String password;
}