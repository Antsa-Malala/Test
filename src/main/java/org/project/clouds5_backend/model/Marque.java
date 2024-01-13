package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marque")
    private int idMarque;
    @NotBlank(message = "Le nom de la marque est obligatoire")
    @Column(name = "nom_marque", nullable = false)
    private String nomMarque;
    @Column(name = "etat_marque")
    private int etat;

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Marque() {
    }

    public Marque(int idMarque, String nomMarque, int etat) {
        this.setIdMarque(idMarque);
        this.setNomMarque(nomMarque);
        this.setEtat(etat);
    }
}
