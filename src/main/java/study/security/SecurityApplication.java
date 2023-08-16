package study.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Collections;

@SpringBootApplication
public class SecurityApplication {

	@Value("${firebase.firebaseConfigPath}")
	private String firebaseConfigPath;

	@Value("${firebase.scope}")
	private String scope;
	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		ClassPathResource serviceAccount = new ClassPathResource(firebaseConfigPath);

		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())
						.createScoped(Collections.singleton(scope)))
				.build();

		FirebaseApp app=FirebaseApp.initializeApp(options);
		return FirebaseMessaging.getInstance(app);
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
