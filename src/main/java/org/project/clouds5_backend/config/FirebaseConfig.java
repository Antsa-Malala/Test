package org.project.clouds5_backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        Resource resource = new ClassPathResource("privateKey/adminsdk.json");
        FileInputStream serviceAccount = new FileInputStream(resource.getFile());

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        }

        return FirebaseMessaging.getInstance();
    }
}