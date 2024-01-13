package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_place")
    private int idPlace;
    @NotNull(message = "La valeur de la place est obligatoire")
    @Column(name = "valeur", nullable = false)
    private int valeur;
    @Column(name = "etat_place")
    private int etat;

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Place() {
    }

    public Place(int idPlace, int valeur, int etat) {
        this.setIdPlace(idPlace);
        this.setValeur(valeur);
        this.setEtat(etat);
    }
}
