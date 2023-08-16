package study.security.Controller;

import com.google.firebase.messaging.FirebaseMessaging;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.security.Data.NotificationMessage;
import study.security.Service.FirebaseMessagingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    FirebaseMessagingService firebaseMessagingService;

    @PostMapping
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage){
        return firebaseMessagingService.sendNotificationByToken(notificationMessage);
    }
}
