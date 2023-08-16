package study.security.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.Data.NotificationMessage;

@Service
@RequiredArgsConstructor
public class FirebaseMessagingService {
    private FirebaseMessaging firebaseMessaging;

    public String sendNotificationByToken(NotificationMessage notificationMessage){
        Notification notification=Notification
                .builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .build();

        Message message= Message.builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .build();

        try{
            firebaseMessaging.send(message);
            return "메세지 전송 성공";
        }catch (FirebaseMessagingException e){
            e.printStackTrace();
            return "메세지 전송 실패";
        }
    }
}
