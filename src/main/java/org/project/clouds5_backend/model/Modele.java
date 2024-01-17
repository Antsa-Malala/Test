package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Modele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modele")
    private int idModele;
    @NotBlank(message = "Le nom du modele est obligatoire")
    @Column(name = "nom_modele", nullable = false,length = 200)
    private String nomModele;
    @ManyToOne
    @JoinColumn(name = "id_marque", nullable = false,referencedColumnName = "id_marque")
    private Marque marque;
    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false,referencedColumnName = "id_categorie")
    private Categorie categorie;
    @Column(name = "etat_modele")
    private int etat;

    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Modele() {
    }

    public Modele(int idModele, String nomModele, Marque marque, Categorie categorie, int etat) {
        this.setIdModele(idModele);
        this.setNomModele(nomModele);
        this.setMarque(marque);
        this.setCategorie(categorie);
        this.setEtat(etat);
    }
}
