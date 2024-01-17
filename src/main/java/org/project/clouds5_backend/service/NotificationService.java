package org.project.clouds5_backend.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public void sendNotification(String token, String title, String body) {
        Message message = Message.builder()
                .putData("title", title)
                .putData("body", body)
                .setToken(token)
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
