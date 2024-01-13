package org.project.clouds5_backend.model;

import jakarta.persistence.*;

@Entity
public class Favoris {
    @Id
    @Column(name = "id_favoris")
    private String idFavoris;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "id_annonce", nullable = false)
    private Annonce annonce;

    public String getIdFavoris() {
        return idFavoris;
    }

    public void setIdFavoris(String idFavoris) {
        this.idFavoris = idFavoris;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Favoris() {
    }

    public Favoris(String idFavoris, Utilisateur utilisateur, Annonce annonce) {
        this.setIdFavoris(idFavoris);
        this.setUtilisateur(utilisateur);
        this.setAnnonce(annonce);
    }
}
