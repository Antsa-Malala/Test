package org.project.clouds5_backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/projetclouds5firebase-firebase-adminsdk-meade-0c641b86f8.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        return FirebaseMessaging.getInstance();
    }
}
