package org.project.clouds5_backend.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Pourcentage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pourcentage")
    int idPourcentage;
    double valeur;
    @Column(name = "date_pourcentage")
    Date datePourcentage;

    public int getIdPourcentage() {
        return idPourcentage;
    }

    public void setIdPourcentage(int idPourcentage) {
        this.idPourcentage = idPourcentage;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Date getDatePourcentage() {
        return datePourcentage;
    }

    public void setDatePourcentage(Date datePourcentage) {
        this.datePourcentage = datePourcentage;
    }

    public Pourcentage(int idPourcentage, double valeur, Date datePourcentage) {
        this.setIdPourcentage(idPourcentage);
        this.setValeur(valeur);
        this.setDatePourcentage(datePourcentage);
    }
    public Pourcentage(){}
}
