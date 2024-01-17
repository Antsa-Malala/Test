package org.project.clouds5_backend.controller;

import jakarta.validation.Valid;
import org.project.clouds5_backend.model.Place;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("places")
public class PlaceController {
    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public ResponseEntity<Reponse<List<Place>>> getAllPlaces() {
        Reponse<List<Place>> reponse = new Reponse<>();
        try {
            List<Place> places = placeService.getAllPlaces();
            if (!places.isEmpty()) {
                reponse.setData(places);
                reponse.setRemarque("Liste des places");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Liste vide");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("suggestion")
    public ResponseEntity<Reponse<List<Place>>> getSuggestion(@RequestParam String insere) {
        Reponse<List<Place>> reponse = new Reponse<>();
        try {
            List<Place> places = placeService.getSuggestion(insere);
            if (!places.isEmpty()) {
                reponse.setData(places);
                reponse.setRemarque("Liste des places similaires");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Liste vide");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reponse<Place>> getPlaceById(@PathVariable Integer id) {
        Reponse<Place> reponse = new Reponse<>();
        try{
            Place place = placeService.getPlaceById(id);
            if(place != null){
                reponse.setData(place);
                reponse.setRemarque("Place trouvee");
                return ResponseEntity.ok().body(reponse);
            }else{
                reponse.setErreur("Place non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse<Place>> createPlace(@Valid @RequestBody Place place) {
        Reponse<Place> reponse = new Reponse<>();
        try {
            Place newPlace = placeService.createPlace(place);
            if (newPlace != null) {
                reponse.setData(newPlace);
                reponse.setRemarque("Place creee");
                return ResponseEntity.status(201).body(reponse);
            } else {
                reponse.setErreur("Place non creee");
                return ResponseEntity.status(400).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse<Place>> updatePlaceById(@PathVariable Integer id, @Valid @RequestBody Place place) {
        Reponse<Place> reponse = new Reponse<>();
        try {
            Place updatedPlace = placeService.updatePlaceById(id, place);
            if (updatedPlace != null) {
                reponse.setData(updatedPlace);
                reponse.setRemarque("Place modifiee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Place non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse<Place>> deletePlaceById(@PathVariable Integer id) {
        Reponse<Place> reponse = new Reponse<>();
        try {
            Place place = placeService.deletePlaceById(id);
            if (place != null) {
                reponse.setData(place);
                reponse.setRemarque("Place supprimee");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Place non trouvee");
                return ResponseEntity.status(404).body(reponse);
            }
        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }

    }



}
