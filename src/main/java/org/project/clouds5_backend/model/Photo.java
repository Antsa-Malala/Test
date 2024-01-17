package org.project.clouds5_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photo")
public class Photo {
    @Id
    private int idPhoto;
    private byte[] photo;
    @Column(name = "id_voiture")
    private String idVoiture;



    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(String idVoiture) {
        this.idVoiture = idVoiture;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Photo() {
    }

    public Photo(int idPhoto,String idVoiture, byte[] photo) {
        this.setIdPhoto(idPhoto);
        this.setPhoto(photo);
        this.setIdVoiture(idVoiture);
    }
}
