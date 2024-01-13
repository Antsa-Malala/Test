package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Energie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_energie")
    private int idEnergie;
    @NotBlank(message = "Le nom de l'energie est obligatoire")
    @Column(name = "nom_energie", nullable = false)
    private String nomEnergie;
    @Column(name = "etat_energie")
    private int etat;

    public int getIdEnergie() {
        return idEnergie;
    }

    public void setIdEnergie(int idEnergie) {
        this.idEnergie = idEnergie;
    }

    public String getNomEnergie() {
        return nomEnergie;
    }

    public void setNomEnergie(String nomEnergie) {
        this.nomEnergie = nomEnergie;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Energie() {
    }

    public Energie(int idEnergie, String nomEnergie, int etat) {
        this.setIdEnergie(idEnergie);
        this.setNomEnergie(nomEnergie);
        this.setEtat(etat);
    }
}
