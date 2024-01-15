package org.project.clouds5_backend.model;

import java.util.Collection;
import java.util.List;

import org.project.clouds5_backend.model.Ville;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@Entity
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {
    @Id
    @Column(name = "id_utilisateur", length = 10)
    private String idUtilisateur;
    @Column(name = "prenom")
    @NotBlank(message = "Le prenom est obligatoire")
    private String prenom;
    @Column(name = "nom")
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @ManyToOne
    @JoinColumn(name = "id_ville", nullable = false)
    @NotNull(message = "La ville est obligatoire")
    private Ville ville;
    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;
    @NotBlank(message = "Le contact est obligatoire")
    @Column(length=20)
    private String contact;
    @Column(name = "mail")
    @NotBlank(message = "L'email est obligatoire")
    private String mail;
    @Column(name = "mot_de_passe", nullable = false)
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String motDePasse;
    private int role;
    @Column(name = "etat_utilisateur")
    private int etat;

    public Utilisateur(String idUtilisateur, String nom, String prenom, Ville ville, String adresse, String contact, String mail, String motDePasse, int role) {
        this.setIdUtilisateur(idUtilisateur);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setVille(ville);
        this.setAdresse(adresse);
        this.setContact(contact);
        this.setMail(mail);
        this.setMotDePasse(motDePasse);
        this.setRole(role);
    }
    public Utilisateur(String idUtilisateur, String nom, String prenom, Ville ville, String adresse, String contact, String mail, String motDePasse,int role, int etat) {
        this.setIdUtilisateur(idUtilisateur);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setVille(ville);
        this.setAdresse(adresse);
        this.setContact(contact);
        this.setMail(mail);
        this.setMotDePasse(motDePasse);
        this.setRole(role);
        this.setEtat(etat);
    }


    public String getFullId() {
        return "USR" + this.idUtilisateur;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getRole() {
        return role;
    }

    public String getPassword() {
        return motDePasse;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Utilisateur() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role==0)
        {
            return List.of(new SimpleGrantedAuthority("USER"));
        }
        return List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
