package org.project.clouds5_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photo")
public class Photo {
    private String photo;
    @Column(name = "id_voiture")
    private String idVoiture;

    public String getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(String idVoiture) {
        this.idVoiture = idVoiture;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Photo() {
    }

    public Photo(String idVoiture, String photo) {
        this.setPhoto(photo);
        this.setIdVoiture(idVoiture);
    }
}
