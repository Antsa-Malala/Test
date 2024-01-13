package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie")
    private int idCategorie;
    @NotBlank(message = "Le nom de la catégorie est obligatoire")
    @Column(name = "nom_categorie", nullable = false,length = 100)
    private String nomCategorie;
    @Column(name = "etat_categorie")
    private int etat;

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Categorie() {
    }

    public Categorie(int idCategorie, String nomCategorie, int etat) {
        this.setIdCategorie(idCategorie);
        this.setNomCategorie(nomCategorie);
        this.setEtat(etat);
    }
}
