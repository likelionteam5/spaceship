package study.security.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.Entity.User;
import study.security.Repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final UserRepository userRepository;
    public void giveKioskBadge(String username) throws Exception {
        Optional<User> opt = userRepository.findByUsername(username);
        User user = opt.orElseThrow(()->new Exception(""));
        user.setKioskBadge(true);
        userRepository.save(user);
    };
    public void giveTrainBadge(String username) throws Exception {
        Optional<User> opt = userRepository.findByUsername(username);
        User user = opt.orElseThrow(()->new Exception(""));
        user.setTrainBadge(true);
        userRepository.save(user);
    };
    public void giveDeliveryBadge(String username) throws Exception {
        Optional<User> opt = userRepository.findByUsername(username);
        User user = opt.orElseThrow(()->new Exception(""));
        user.setDeliveryBadge(true);
        userRepository.save(user);
    };

}
