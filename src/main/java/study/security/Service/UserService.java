package study.security.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.security.Entity.Authority;
import study.security.Entity.User;
import study.security.Repository.AuthorityRepository;
import study.security.Repository.UserRepository;
import study.security.Util.SecurityUtil;
import study.security.dto.JoinDto;
import study.security.dto.UserDto;
import study.security.dto.MyPageDto;
import study.security.exception.DuplicateMemberException;
import study.security.exception.NotFoundMemberException;

import java.util.Collections;
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto signup(JoinDto joinDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(joinDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        if (!joinDto.getPassword().equals(joinDto.getCk_password())){
            throw new DuplicateMemberException("비밀번호가 다릅니다");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        authorityRepository.save(authority);

        User user = User.builder()
                .username(joinDto.getUsername())
                .password(passwordEncoder.encode(joinDto.getPassword()))
                .name(joinDto.getName())
                .birth(joinDto.getBirth())
                .gender(joinDto.getGender())
                .phoneNumber(joinDto.getPhoneNumber())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return UserDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public Boolean checkUsernameDuplicate(String username){
        if(userRepository.existsByUsername(username)) return true;
        return false;
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username) {
        return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
    public MyPageDto getUserProfile(String username) {
        User user = userRepository.findByUsername(username).get();

        if (user != null) {
            MyPageDto userProfileDTO = new MyPageDto();
            userProfileDTO.setName(user.getName());
            userProfileDTO.setBirth(user.getBirth());
            userProfileDTO.setGender(user.getGender());
            return userProfileDTO;
        } else {
            return null; // 사용자가 없는 경우에 대한 처리
        }
    }
}