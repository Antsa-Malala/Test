package org.project.clouds5_backend.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Annonce {
    @Id
    @Column(name = "id_annonce",length = 10)
    private String idAnnonce;
    @Column(name = "date_annonce", nullable = false)
    private Date dateAnnonce;
    private double prix;
    @ManyToOne
    @JoinColumn(name = "id_voiture", nullable = false)
    private Voiture voiture;
    @ManyToOne
    @JoinColumn(name = "id_ville", nullable = false)
    private Ville ville;
    private String description;
    @Column(name = "etat_annonce")
    private int etat;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    public String getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(String idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Date getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(Date dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Annonce() {
    }

    public Annonce(String idAnnonce, Date dateAnnonce, double prix, Voiture voiture, Ville ville, String description,
                   int etat, Utilisateur utilisateur) {
        this.setIdAnnonce(idAnnonce);
        this.setDateAnnonce(dateAnnonce);
        this.setPrix(prix);
        this.setVoiture(voiture);
        this.setVille(ville);
        this.setDescription(description);
        this.setEtat(etat);
        this.setUtilisateur(utilisateur);
    }

    public Annonce(Date dateAnnonce, double prix, Voiture voiture, Ville ville, String description,
                   int etat, Utilisateur utilisateur) {
        this.setDateAnnonce(dateAnnonce);
        this.setPrix(prix);
        this.setVoiture(voiture);
        this.setVille(ville);
        this.setDescription(description);
        this.setEtat(etat);
        this.setUtilisateur(utilisateur);
    }
}
