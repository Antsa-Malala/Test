package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Voiture {
    @Id
    @Column(name = "id_voiture")
    private String idVoiture;
    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie categorie;
    @ManyToOne
    @JoinColumn(name = "id_marque", nullable = false)
    private Marque marque;
    @ManyToOne
    @JoinColumn(name = "id_modele", nullable = false)
    private Modele modele;
    @ManyToOne
    @JoinColumn(name = "id_energie", nullable = false)
    private Energie energie;
    @ManyToOne
    @JoinColumn(name = "id_boite", nullable = false)
    private Boite boite;
    private double consommation;
    @NotNull(message = "La place est obligatoire")
    @Column(name = "nb_place", nullable = false)
    private int Nbplace;
    @ManyToOne
    @JoinColumn(name = "id_porte", nullable = false)
    private Porte porte;
    private double kilometrage;
    @ManyToOne
    @JoinColumn(name = "id_couleur", nullable = false)
    private Couleur couleur;
    @Column(name = "etat_voiture")
    private int etat;

    public String getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(String idVoiture) {
        this.idVoiture = idVoiture;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Energie getEnergie() {
        return energie;
    }

    public void setEnergie(Energie energie) {
        this.energie = energie;
    }

    public Boite getBoite() {
        return boite;
    }

    public void setBoite(Boite boite) {
        this.boite = boite;
    }

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public int getNbplace() {
        return Nbplace;
    }

    public void setNbplace(int nbplace) {
        Nbplace = nbplace;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Voiture() {
    }

    public Voiture(String idVoiture, Categorie categorie, Marque marque, Modele modele, Energie energie, Boite boite,
            double consommation, int Nbplace, Porte porte, double kilometrage, Couleur couleur, int etat) {
        this.setIdVoiture(idVoiture);
        this.setCategorie(categorie);
        this.setMarque(marque);
        this.setModele(modele);
        this.setEnergie(energie);
        this.setBoite(boite);
        this.setConsommation(consommation);
        this.setNbplace(Nbplace);
        this.setPorte(porte);
        this.setKilometrage(kilometrage);
        this.setCouleur(couleur);
        this.setEtat(etat);
    }
}
