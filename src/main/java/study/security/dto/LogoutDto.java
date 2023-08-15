package study.security.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutDto {
    @NotNull
    @Column(length = 25)
    private String username;

    @NotNull
    @Column(length = 40)
    private String password;
}
