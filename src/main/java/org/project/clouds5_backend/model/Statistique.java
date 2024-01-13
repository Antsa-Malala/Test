package org.project.clouds5_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Statistique {
    @Id
    private String libelle;
    private double nombre;

    public Statistique() {
    }

    public Statistique(String libelle, double nombre) {
        this.libelle = libelle;
        this.nombre = nombre;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
    }
}
