package org.project.clouds5_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Document(collection = "inbox")
public class Inbox {

    @Id
    @Field("_id")
    private String id;

    @Field("id_utilisateur_1")
    private String idUtilisateur1;

    @Field("id_utilisateur_2")
    private String idUtilisateur2;

    @Field("date_message")
    private LocalDateTime dateMessage;

    @Field("last")
    private String lastMessage;


    // GETTERS AND SETTERS
    public String getIdUtilisateur1() {
        return idUtilisateur1;
    }

    public void setIdUtilisateur1(String idUtilisateur1) {
        this.idUtilisateur1 = idUtilisateur1;
    }

    public String getIdUtilisateur2() {
        return idUtilisateur2;
    }

    public void setIdUtilisateur2(String idUtilisateur2) {
        this.idUtilisateur2 = idUtilisateur2;
    }

    public LocalDateTime getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(LocalDateTime dateMessage) {
        this.dateMessage = dateMessage;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
