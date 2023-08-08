package study.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import study.security.Entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Size(min = 3, max = 25)
    @NotNull
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 200)
    private String password;

    @NotNull
    @Size(max = 20)
    private String name;
    @NotNull
    @Size(max = 10)
    private String birth;
    @NotNull
    @Size(max = 5)
    private String gender;
    @NotNull
    @Size(max = 20)
    private String phoneNumber;

    private Set<AuthorityDto> authorityDtoSet;

    public static UserDto from(User user) {
        if(user == null) return null;

        return UserDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .birth(user.getBirth())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}