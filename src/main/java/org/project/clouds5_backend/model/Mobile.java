package org.project.clouds5_backend.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "mobileToken")
public class Mobile {
    @Field("id_utilisateur")
    private String idUtilisateur;

    @Field("token")
    private String token;

    // GETTERS AND SETTERS
    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
