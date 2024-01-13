package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Porte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_porte")
    private int idPorte;
    @NotNull(message = "La valeur de la porte est obligatoire")
    @Column(name = "valeur", nullable = false)
    private int valeur;
    @Column(name = "etat_porte")
    private int etat;

    public int getIdPorte() {
        return idPorte;
    }

    public void setIdPorte(int idPorte) {
        this.idPorte = idPorte;
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

    public Porte() {
    }

    public Porte(int idPorte, String nomPorte, int etat) {
        this.setIdPorte(idPorte);
        this.setValeur(valeur);
        this.setEtat(etat);
    }
}
