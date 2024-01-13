package org.project.clouds5_backend.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Validation {
    @Id
    @Column(name = "id_validation")
    private String idValidation;
    @Column(name = "date_validation", nullable = false)
    private Date dateValidation;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "id_annonce", nullable = false)
    private Annonce annonce;

    public String getIdValidation() {
        return idValidation;
    }

    public void setIdValidation(String idValidation) {
        this.idValidation = idValidation;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
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

    public Validation() {
    }

    public Validation(String idValidation, Date dateValidation, Utilisateur utilisateur, Annonce annonce) {
        this.setIdValidation(idValidation);
        this.setDateValidation(dateValidation);
        this.setUtilisateur(utilisateur);
        this.setAnnonce(annonce);
    }
}
