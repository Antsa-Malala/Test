package org.project.clouds5_backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {


    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        Resource resource = new ClassPathResource(firebaseConfigPath);
        InputStream serviceAccount = new ClassPathResource(firebaseConfigPath).getInputStream();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        }

        return FirebaseMessaging.getInstance();
    }
}