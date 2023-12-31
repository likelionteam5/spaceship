package study.security.Controller;

import org.hibernate.mapping.Join;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import study.security.Service.UserService;
import study.security.dto.JoinDto;
import study.security.dto.UserDto;
import study.security.dto.MyPageDto;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }

    @PostMapping("/signup")

    public ResponseEntity<UserDto> signup(@Valid @RequestBody JoinDto joinDto) {
        return ResponseEntity.ok(userService.signup(joinDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }

    @GetMapping("/myPage/{username}") // 마이페이지
    public MyPageDto getUserProfile(@PathVariable String username) {
        return userService.getUserProfile(username);
    }
}