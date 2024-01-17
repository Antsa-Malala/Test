package org.project.clouds5_backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.project.clouds5_backend.model.*;
import org.project.clouds5_backend.repository.AnnonceRepository;
import org.project.clouds5_backend.repository.ValidationRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final UtilisateurService utilisateurService;
    private final ValidationService validationService;
    private final RefusService refusService;
    private final VenteService venteService;

    public AnnonceService(AnnonceRepository annonceRepository,UtilisateurService utilisateurService,ValidationService validationService,RefusService refusService,VenteService venteService) {
        this.annonceRepository = annonceRepository;
        this.utilisateurService =utilisateurService;
        this.validationService =validationService;
        this.refusService =refusService;
        this.venteService=venteService;
    }

    public List<Annonce> getAllAnnonces() {
        List<Annonce> annonces = annonceRepository.findByEtatNot(10);
        if (annonces.isEmpty()) {
            return Collections.emptyList();
        } else {
            return annonces;
        }
    }

    public List<Annonce> getAnnonceValidee() {
        List<Annonce> annonces = annonceRepository.findByEtat(20);
        if (annonces.isEmpty()) {
            return Collections.emptyList();
        } else {
            return annonces;
        }
    }

    public List<Annonce> getAnnonceNonValide() {
        List<Annonce> annonces = annonceRepository.findByEtat(0);
        if (annonces.isEmpty()) {
            return Collections.emptyList();
        } else {
            return annonces;
        }
    }

    public List<Annonce> getAnnonceVendue() {
        List<Annonce> annonces = annonceRepository.findByEtat(30);
        if (annonces.isEmpty()) {
            return Collections.emptyList();
        } else {
            return annonces;
        }
    }

    public List<Annonce> getAnnonceRefusee() {
        List<Annonce> annonces = annonceRepository.findByEtat(10);
        if (annonces.isEmpty()) {
            return Collections.emptyList();
        } else {
            return annonces;
        }
    }

    public Annonce getAnnonceById(String id) {
        Annonce annonce = annonceRepository.findByIdAnnonceAndEtatNot(id, 10);
        if (annonce == null) {
            return null;
        } else {
            return annonce;
        }
    }

    public Annonce createAnnonce(Annonce annonce) {
        try {
            String idAnnonce=annonceRepository.getNextValSequence();
            annonce.setIdAnnonce(idAnnonce);
            return annonceRepository.save(annonce);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Annonce updateAnnonceById(String id, Annonce annonce) {
        Optional<Annonce> optionalAnnonce = Optional.ofNullable(annonceRepository.findByIdAnnonceAndEtatNot(id, 10));
        if (optionalAnnonce.isPresent()) {
            Annonce annonceToUpdate = optionalAnnonce.get();
            annonceToUpdate.setDateAnnonce(annonce.getDateAnnonce());
            annonceToUpdate.setPrix(annonce.getPrix());
            annonceToUpdate.setVoiture(annonce.getVoiture());
            annonceToUpdate.setVille(annonce.getVille());
            annonceToUpdate.setDescription(annonce.getDescription());
            annonceToUpdate.setEtat(annonce.getEtat());
            annonceToUpdate.setUtilisateur(annonce.getUtilisateur());
            annonceRepository.save(annonceToUpdate);
            return annonceToUpdate;
        } else {
            throw new RuntimeException("Annonce non trouvee");
        }
    }

    public Annonce valider(String id) {
        Optional<Annonce> optionalAnnonce = Optional.ofNullable(annonceRepository.findByIdAnnonceAndEtatNot(id, 10));
        if (optionalAnnonce.isPresent()) {
            Annonce annonceToUpdate = optionalAnnonce.get();
            annonceToUpdate.setEtat(20);
            String idValidation=validationService.getNextValSequence();
            Utilisateur connecte=utilisateurService.getConnected();
            Validation a=new Validation(idValidation,new Date(System.currentTimeMillis()),connecte,annonceToUpdate);
            validationService.createValidation(a);
            annonceRepository.save(annonceToUpdate);
            return annonceToUpdate;
        } else {
            throw new RuntimeException("Annonce non trouvee");
        }
    }

    public Annonce refuser(String id) {
        Optional<Annonce> optionalAnnonce = Optional.ofNullable(annonceRepository.findByIdAnnonceAndEtatNot(id, 10));
        if (optionalAnnonce.isPresent()) {
            Annonce annonceToUpdate = optionalAnnonce.get();
            annonceToUpdate.setEtat(10);
            String idRefus=refusService.getNextValSequence();
            Utilisateur connecte=utilisateurService.getConnected();
            Refus a=new Refus(idRefus,new Date(System.currentTimeMillis()),connecte,annonceToUpdate);
            refusService.createRefus(a);
            annonceRepository.save(annonceToUpdate);
            return annonceToUpdate;
        } else {
            throw new RuntimeException("Annonce non trouvee");
        }
    }

    public Annonce vendre(String id) {
        Optional<Annonce> optionalAnnonce = Optional.ofNullable(annonceRepository.findByIdAnnonceAndEtatNot(id, 10));
        if (optionalAnnonce.isPresent()) {
            Annonce annonceToUpdate = optionalAnnonce.get();
            annonceToUpdate.setEtat(30);
            String idVente=venteService.getNextValSequence();
            Utilisateur connecte=utilisateurService.getConnected();
            Vente a=new Vente(idVente,annonceToUpdate,new Date(System.currentTimeMillis()));
            venteService.createVente(a);
            annonceRepository.save(annonceToUpdate);
            return annonceToUpdate;
        } else {
            throw new RuntimeException("Annonce non trouvee");
        }
    }


    public Annonce deleteAnnonceById(String id) {
        Optional<Annonce> optionalAnnonce = Optional.ofNullable(annonceRepository.findByIdAnnonceAndEtatNot(id, 10));
        if (optionalAnnonce.isPresent()) {
            Annonce annonceToDelete = optionalAnnonce.get();
            annonceToDelete.setEtat(10);
            annonceRepository.save(annonceToDelete);
            return annonceToDelete;
        } else {
            throw new RuntimeException("Annonce non trouvee");
        }
    }

    public List<Annonce> rechercheAvancee(
            String motCle,
            Date dateDebut,
            Date dateFin,
            Double prixMin,
            Double prixMax,
            Integer categorie,
            Integer marque,
            Integer model,
            Integer boite,
            Integer energie,
            Integer place,
            Integer porte,
            Integer couleur,
            Integer ville,
            String utilisateur,
            Double kilometrageMin,
            Double kilometrageMax,
            Double consommationMin,
            Double consommationMax
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Annonce> criteriaQuery = criteriaBuilder.createQuery(Annonce.class);
        Root<Annonce> root = criteriaQuery.from(Annonce.class);

        List<Predicate> predicates = new ArrayList<>();
        if(motCle != null && !motCle.isEmpty()) {
            String motCleLower = motCle.toLowerCase();


            Predicate predicate0 = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + motCleLower + "%");
            Predicate predicate1 = criteriaBuilder.like(criteriaBuilder.lower(root.get("voiture").get("modele").get("nomModele")), "%" + motCleLower + "%");
            Predicate predicate2 = criteriaBuilder.like(criteriaBuilder.lower(root.get("voiture").get("modele").get("marque").get("nomMarque")), "%" + motCleLower + "%");
            Predicate predicate3 = criteriaBuilder.like(criteriaBuilder.lower(root.get("voiture").get("modele").get("categorie").get("nomCategorie")), "%" + motCleLower + "%");
            Predicate predicate4 = criteriaBuilder.like(criteriaBuilder.lower(root.get("voiture").get("boite").get("nomBoite")), "%" + motCleLower + "%");
            Predicate predicate5 = criteriaBuilder.like(criteriaBuilder.lower(root.get("voiture").get("energie").get("nomEnergie")), "%" + motCleLower + "%");
            Predicate predicate6 = criteriaBuilder.like(criteriaBuilder.lower(root.get("utilisateur").get("nom")), "%" + motCleLower + "%");
            Predicate predicate7 = criteriaBuilder.like(criteriaBuilder.lower(root.get("voiture").get("couleur").get("nomCouleur")), "%" + motCleLower + "%");
            Predicate predicate8 = criteriaBuilder.like(criteriaBuilder.lower(root.get("ville").get("nomVille")), "%" + motCleLower + "%");
            Predicate predicate9 = criteriaBuilder.like(criteriaBuilder.lower(root.get("utilisateur").get("prenom")), "%" + motCleLower + "%");

            predicates.add(criteriaBuilder.or(predicate0, predicate1, predicate2, predicate3, predicate4, predicate5, predicate6, predicate7, predicate8, predicate9));
        }
        if(dateDebut != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateAnnonce"), dateDebut));
        }
        if(dateFin != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateAnnonce"), dateFin));
        }
        if(prixMin != null && prixMin != 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("prix"), prixMin));
        }
        if(prixMax != null && prixMax != 0) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("prix"), prixMax));
        }
        if(categorie != null && categorie != 0) {
            predicates.add(criteriaBuilder.equal(root.get("voiture").get("modele").get("categorie").get("idCategorie"), categorie));
        }
        if(marque != null && marque != 0) {
            predicates.add(criteriaBuilder.equal(root.get("voiture").get("modele").get("marque").get("idMarque"), marque));
        }
        if(model != null && model != 0) {
            predicates.add(criteriaBuilder.equal(root.get("voiture").get("modele").get("idModele"), model));
        }
        if(boite != null && boite != 0) {
            predicates.add(criteriaBuilder.equal(root.get("voiture").get("boite").get("idBoite"), boite));
        }
        if(energie != null && energie != 0) {
            predicates.add(criteriaBuilder.equal(root.get("voiture").get("energie").get("idEnergie"), energie));
        }
        if(place != null && place != 0) {
            predicates.add(criteriaBuilder.equal(root.get("voiture").get("Nbplace"), place));
        }
        if(porte != null && porte != 0) {
            predicates.add(criteriaBuilder.equal(root.get("voiture").get("porte").get("idPorte"), porte));
        }
        if(couleur != null && couleur != 0) {
            predicates.add(criteriaBuilder.equal(root.get("voiture").get("couleur").get("idCouleur"), couleur));
        }
        if(ville != null && ville != 0) {
            predicates.add(criteriaBuilder.equal(root.get("ville").get("idVille"), ville));
        }
        if(utilisateur != null) {
            predicates.add(criteriaBuilder.equal(root.get("utilisateur").get("idUtilisateur"), utilisateur));
        }
        if(kilometrageMin != null && kilometrageMin != 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("voiture").get("kilometrage"), kilometrageMin));
        }
        if(kilometrageMax != null && kilometrageMax != 0) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("voiture").get("kilometrage"), kilometrageMax));
        }
        if(consommationMin != null && consommationMin != 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("voiture").get("consommation"), consommationMin));
        }
        if(consommationMax != null && consommationMax != 0) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("voiture").get("consommation"), consommationMax));
        }
        predicates.add(criteriaBuilder.notEqual(root.get("etat"), 10));
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Annonce> getHistoriqueByUser(Utilisateur utilisateur){
        return annonceRepository.findByUtilisateurOrderByDateAnnonceDesc(utilisateur);
    }
}
