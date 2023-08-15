package study.security.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.security.Jwt.TokenProvider;
import study.security.Service.TestService;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final TokenProvider tokenProvider;
    @PostMapping("/kiosk/test/badge")
    public void giveKioskBadge(@RequestParam("pass")Boolean pass, @RequestHeader("Authorization") String token) throws Exception {
        String username = tokenProvider.getUsernameFromToken(token);
        if(pass){
            testService.giveKioskBadge(username);
        }else throw new Exception("시험에 통과하지 못했습니다.");
    }
    @PostMapping("/kiosk/train/badge")
    public void giveTrainBadge(@RequestParam("pass")Boolean pass, @RequestHeader("Authorization") String token) throws Exception {
        String username = tokenProvider.getUsernameFromToken(token);
        if(pass){
            testService.giveTrainBadge(username);
        }else throw new Exception("시험에 통과하지 못했습니다.");
    }
    @PostMapping("/kiosk/delivery/badge")
    public void giveDeliveryBadge(@RequestParam("pass")Boolean pass, @RequestHeader("Authorization") String token)  throws Exception {
        String username = tokenProvider.getUsernameFromToken(token);
        if(pass){
            testService.giveDeliveryBadge(username);
        }else throw new Exception("시험에 통과하지 못했습니다.");
    }
}
