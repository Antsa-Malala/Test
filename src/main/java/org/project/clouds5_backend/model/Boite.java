package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Boite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boite")
    private int idBoite;
    @NotBlank(message = "Le nom de la boite est obligatoire")
    @Column(name = "nom_boite", nullable = false)
    private String nomBoite;
    @Column(name = "etat_boite")
    private int etat;

    public int getIdBoite() {
        return idBoite;
    }

    public void setIdBoite(int idBoite) {
        this.idBoite = idBoite;
    }

    public String getNomBoite() {
        return nomBoite;
    }

    public void setNomBoite(String nomBoite) {
        this.nomBoite = nomBoite;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Boite() {
    }

    public Boite(int idBoite, String nomBoite, int etat) {
        this.setIdBoite(idBoite);
        this.setNomBoite(nomBoite);
        this.setEtat(etat);
    }
}
