package org.project.clouds5_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

@Entity
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commission")
    private int idCommission;
    @ManyToOne
    @JoinColumn(name = "id_annonce", nullable = false,referencedColumnName = "id_annonce")
    private Annonce annonce;
    @Column(name = "date_commission", nullable = false)
    private Date dateCommission;
    private double montant;

    public void setIdCommission(int idCommission)
    {
        this.idCommission=idCommission;
    }
    public int getIdCommission()
    {
        return this.idCommission;
    }
    public void setDateCommission(Date dateCommission)
    {
        this.dateCommission=dateCommission;
    }
    public Date getDateCommission()
    {
        return this.dateCommission;
    }

    public void setAnnonce(Annonce annonce)
    {
        this.annonce=annonce;
    }

    public Annonce getAnnonce()
    {
        return this.annonce;
    }

    public void setMontant(double montant)
    {
        this.montant=montant;
    }

    public double getMontant()
    {
        return this.montant;
    }
    public Commission(){

    }
    public Commission(int idCommission,Annonce annonce,Date dateCommission,double montant){
        this.setIdCommission(idCommission);
        this.setAnnonce(annonce);
        this.setDateCommission(dateCommission);
        this.setMontant(montant);
    }

    public Commission(Annonce annonce,Date dateCommission,double montant){
        this.setAnnonce(annonce);
        this.setDateCommission(dateCommission);
        this.setMontant(montant);
    }
}

