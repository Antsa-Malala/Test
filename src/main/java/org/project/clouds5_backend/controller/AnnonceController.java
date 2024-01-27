package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Annonce;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.project.clouds5_backend.model.Annonce;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.model.Utilisateur;
import org.project.clouds5_backend.service.AnnonceService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("annonces")
public class AnnonceController {
    private final AnnonceService annonceService;

    private final ValidationService validationService;
    private  final UtilisateurService utilisateurService;

    @Autowired
    public AnnonceController(AnnonceService annonceService,ValidationService validationService,UtilisateurService utilisateurService) {
        this.annonceService = annonceService;
        this.validationService = validationService;
        this.utilisateurService = utilisateurService;
    }


    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Reponse<List<Annonce>>> getAnnonceByUser(@PathVariable String id) {
        Reponse<List<Annonce>> reponse = new Reponse<>();
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
            if(utilisateur != null){
                List<Annonce> result = annonceService.getHistoriqueByUser(utilisateur);
                if (result != null) {
                    reponse.setData(result);

                    return ResponseEntity.status(200).body(reponse);
                } else {
                    reponse.setErreur("Erreur sur la liste des annonces par utilisateur");
                    return ResponseEntity.status(400).body(reponse);
                }
            }else  {
                reponse.setErreur("User not found");
                return ResponseEntity.status(400).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Annonce>>> getAllAnnonces() {
        Reponse<List<Annonce>> reponse = new Reponse<>();
        try {
            List<Annonce> annonces = annonceService.getAllAnnonces();
            if (!annonces.isEmpty()) {
                reponse.setData(annonces);
                reponse.setRemarque("Liste des annonces");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Liste vide");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/valide")
    public ResponseEntity<Reponse<List<Annonce>>> getAnnonceValide() {
        Reponse<List<Annonce>> reponse = new Reponse<>();
        try {
            List<Annonce> annonces = annonceService.getAnnonceValidee();
            if (!annonces.isEmpty()) {
                reponse.setData(annonces);
                reponse.setRemarque("Liste des annonces validee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Liste vide");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/invalide")
    public ResponseEntity<Reponse<List<Annonce>>> getAnnonceNonValide() {
        Reponse<List<Annonce>> reponse = new Reponse<>();
        try {
            List<Annonce> annonces = annonceService.getAnnonceNonValide();
            if (!annonces.isEmpty()) {
                reponse.setData(annonces);
                reponse.setRemarque("Liste des annonces non validees");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Liste vide");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reponse<Annonce>> getAnnonceById(@PathVariable String id) {
        Reponse<Annonce> reponse = new Reponse<>();
        try {
            Annonce annonce = annonceService.getAnnonceById(id);
            if (annonce != null) {
                reponse.setData(annonce);
                reponse.setRemarque("Annonce trouvee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Annonce non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
    @GetMapping("/front/{id}")
    public ResponseEntity<Reponse<Annonce>> getAnnonceByIdFront(@PathVariable String id) {
        Reponse<Annonce> reponse = new Reponse<>();
        try {
            Annonce annonce = annonceService.getAnnonceByIdFront(id);
            if (annonce != null) {
                reponse.setData(annonce);
                reponse.setRemarque("Annonce trouvee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Annonce non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Annonce>> createAnnonce(@RequestBody Annonce annonce) {
        Reponse<Annonce> reponse = new Reponse<>();
        try {
            Annonce annonceCreated = annonceService.createAnnonce(annonce);
            if (annonceCreated != null) {
                reponse.setData(annonceCreated);
                reponse.setRemarque("Annonce creee");
                return ResponseEntity.status(201).body(reponse);
            } else {
                reponse.setErreur("Annonce non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Annonce>> updateAnnonce(@PathVariable String id,
            @Valid @RequestBody Annonce annonce) {
        Reponse<Annonce> reponse = new Reponse<>();
        try {
            Annonce annonce1 = annonceService.updateAnnonceById(id, annonce);
            if (annonce1 != null) {
                reponse.setData(annonce1);
                reponse.setRemarque("Annonce modifiee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Annonce non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/valider/{id}")
    public ResponseEntity<Reponse<Annonce>> valider(@PathVariable String id) {
        Reponse<Annonce> reponse = new Reponse<>();
        try {
            Annonce annonce1 = annonceService.valider(id);
            if (annonce1 != null) {
                reponse.setData(annonce1);
                reponse.setRemarque("Annonce validee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Annonce non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/refuser/{id}")
    public ResponseEntity<Reponse<Annonce>> refuser(@PathVariable String id) {
        Reponse<Annonce> reponse = new Reponse<>();
        try {
            Annonce annonce1 = annonceService.refuser(id);
            if (annonce1 != null) {
                reponse.setData(annonce1);
                reponse.setRemarque("Annonce refusee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Annonce non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/vendre/{id}")
    public ResponseEntity<Reponse<Annonce>> vendre(@PathVariable String id) {
        Reponse<Annonce> reponse = new Reponse<>();
        try {
            Annonce annonce1 = annonceService.vendre(id);
            if (annonce1 != null) {
                reponse.setData(annonce1);
                reponse.setRemarque("Voiture vendue");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Annonce non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Annonce>> deleteAnnonce(@PathVariable String id) {
        Reponse<Annonce> reponse = new Reponse<>();
        try {
            Annonce annonce = annonceService.deleteAnnonceById(id);
            if (annonce != null) {
                reponse.setData(annonce);
                reponse.setRemarque("Annonce supprimee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Annonce non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Reponse<List<Annonce>>> searchAnnonce(
            @RequestParam(required = false) String motCle,
            @RequestParam(required = false) Date dateDebut,
            @RequestParam(required = false) Date dateFin,
            @RequestParam(required = false) Double prixMin,
            @RequestParam(required = false) Double prixMax,
            @RequestParam(required = false) Integer idCategorie,
            @RequestParam(required = false) Integer idMarque,
            @RequestParam(required = false) Integer idModel,
            @RequestParam(required = false) Integer idBoite,
            @RequestParam(required = false) Integer idEnergie,
            @RequestParam(required = false) Integer idPlace,
            @RequestParam(required = false) Integer idPorte,
            @RequestParam(required = false) Integer idCouleur,
            @RequestParam(required = false) Integer idVille,
            @RequestParam(required = false) String idUtilisateur,
            @RequestParam(required = false) Double kilometrageMin,
            @RequestParam(required = false) Double kilometrageMax,
            @RequestParam(required = false) Double consommationMin,
            @RequestParam(required = false) Double consommationMax
    ) {
        Reponse<List<Annonce>> reponse = new Reponse<>();
        try{
            List<Annonce> annonces = annonceService.rechercheAvancee(motCle, dateDebut, dateFin, prixMin, prixMax, idCategorie, idMarque, idModel, idBoite, idEnergie, idPlace, idPorte, idCouleur, idVille, idUtilisateur, kilometrageMin, kilometrageMax, consommationMin, consommationMax);
            if(!annonces.isEmpty()){
                reponse.setData(annonces);
                reponse.setRemarque("Annonces trouvees");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Aucune annonce trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
