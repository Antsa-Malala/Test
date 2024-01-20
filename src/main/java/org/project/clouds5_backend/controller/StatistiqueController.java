package org.project.clouds5_backend.controller;

import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.model.Statistique;
import org.project.clouds5_backend.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("statistiques")
public class StatistiqueController {
    private final StatistiqueService statistiqueService;

    @Autowired
    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @GetMapping("/nbUtilisateurs")
    public ResponseEntity<Reponse<Statistique>> getNbUtilisateurs() {
        Reponse<Statistique> reponse = new Reponse<>();
        try{
            Statistique statistique = statistiqueService.getNbUtilisateurs();
            reponse.setData(statistique);
            reponse.setRemarque("Nombre d'utilisateurs");
            return ResponseEntity.ok().body(reponse);
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/nbPubliees")
    public ResponseEntity<Reponse<Statistique>> getNbPubliees() {
        Reponse<Statistique> reponse = new Reponse<>();
        try{
            Statistique statistique = statistiqueService.getNbPublie();
            reponse.setData(statistique);
            reponse.setRemarque("Nombre d'annonces publiees");
            return ResponseEntity.ok().body(reponse);
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/nbVendues")
    public ResponseEntity<Reponse<Statistique>> getNbVendues() {
        Reponse<Statistique> reponse = new Reponse<>();
        try{
            Statistique statistique = statistiqueService.getNbVendu();
            reponse.setData(statistique);
            reponse.setRemarque("Nombre de voitures vendues");
            return ResponseEntity.ok().body(reponse);
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/venduByPrix")
    public ResponseEntity<Reponse<Statistique>> getVenduByPrix(@RequestParam("prix1") double prix1,@RequestParam("prix2") double prix2) {
        Reponse<Statistique> reponse = new Reponse<>();
        try{
            Statistique statistique = statistiqueService.getVenduByPrix(prix1, prix2);
            reponse.setData(statistique);
            reponse.setRemarque("Nombre de véhicules vendus entre " + prix1 + " et " + prix2);
            return ResponseEntity.ok().body(reponse);
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/annonceByMarque")
    public ResponseEntity<Reponse<List<Statistique>>> getAnnonceByMarque() {
        Reponse<List<Statistique>> reponse = new Reponse<>();
        try{
            List<Statistique> statistique = statistiqueService.getAnnonceByMarque();
            if (!statistique.isEmpty()) {
                reponse.setData(statistique);
                reponse.setRemarque("Nombre d'annonces par marque");
                return ResponseEntity.ok().body(reponse);
            }else {
                reponse.setErreur("Marque inexistante");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/venduByMarque")
    public ResponseEntity<Reponse<List<Statistique>>> getVenduByMarque() {
        Reponse<List<Statistique>> reponse = new Reponse<>();
        try{
            List<Statistique> statistique = statistiqueService.getVenduByMarque();
            if (!statistique.isEmpty()) {
                reponse.setData(statistique);
                reponse.setRemarque("Nombre de vendues par marque");
                return ResponseEntity.ok().body(reponse);
            }else {
                reponse.setErreur("Aucun vendu");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/beneficeByMois")
    public ResponseEntity<Reponse<Statistique>> getBeneficeByMois(@RequestParam("mois") int mois,@RequestParam("annee") int annee) {
        Reponse<Statistique> reponse = new Reponse<>();
        try{
            Statistique statistique = statistiqueService.getBeneficeByMois(mois, annee);
            reponse.setData(statistique);
            reponse.setRemarque("Bénéfice du mois " + mois + " de l'année " + annee);
            return ResponseEntity.ok().body(reponse);
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }
}
