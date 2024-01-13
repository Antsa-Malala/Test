package org.project.clouds5_backend.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Refus {
    @Id
    @Column(name = "id_refus")
    private String idRefus;
    @Column(name = "date_refus", nullable = false)
    private Date dateRefus;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "id_annonce", nullable = false)
    private Annonce annonce;

    public String getIdRefus() {
        return idRefus;
    }

    public void setIdRefus(String idRefus) {
        this.idRefus = idRefus;
    }

    public Date getDateRefus() {
        return dateRefus;
    }

    public void setDateRefus(Date dateRefus) {
        this.dateRefus = dateRefus;
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

    public Refus() {
    }

    public Refus(String idRefus, Date dateRefus, Utilisateur utilisateur, Annonce annonce) {
        this.setIdRefus(idRefus);
        this.setDateRefus(dateRefus);
        this.setUtilisateur(utilisateur);
        this.setAnnonce(annonce);
    }
}
