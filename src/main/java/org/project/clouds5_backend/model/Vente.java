package org.project.clouds5_backend.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Vente {
    @Id
    @Column(name = "id_vente",length=10)
    private String idVente;
    @ManyToOne
    @JoinColumn(name = "id_annonce", nullable = false)
    private Annonce annonce;
    private Date dateVente;

    public String getIdVente() {
        return idVente;
    }

    public void setIdVente(String idVente) {
        this.idVente = idVente;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public Vente() {
    }

    public Vente(String idVente, Annonce annonce, Date dateVente) {
        this.setIdVente(idVente);
        this.setAnnonce(annonce);
        this.setDateVente(dateVente);
    }
}
