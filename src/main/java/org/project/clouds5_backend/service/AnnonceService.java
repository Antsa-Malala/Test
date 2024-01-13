package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.*;
import org.project.clouds5_backend.repository.AnnonceRepository;
import org.project.clouds5_backend.repository.ValidationRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
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
}
