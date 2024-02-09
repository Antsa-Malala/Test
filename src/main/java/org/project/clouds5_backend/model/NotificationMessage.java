package org.project.clouds5_backend.model;

import java.util.Map;

import lombok.Data;

@Data
public class NotificationMessage {
    private String recipientToken;
    private String title;
    private String body;
    private Map<String, String> data;


    public NotificationMessage(String recipientToken, String title, String body) {
        this.recipientToken = recipientToken;
        this.title = title;
        this.body = body;
    }
}