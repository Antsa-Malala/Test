package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ville")
    private int idVille;
    @NotBlank(message = "Le nom de la ville est obligatoire")
    @Column(name = "nom_ville", nullable = false,length = 100)
    private String nomVille;
    @Column(name = "etat_ville")
    private int etat;

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Ville() {
    }

    public Ville(int idVille, String nomVille, int etat) {
        this.setIdVille(idVille);
        this.setNomVille(nomVille);
        this.setEtat(etat);
    }

    public Ville(int idVille){
        this.setIdVille(idVille);
    }
}
