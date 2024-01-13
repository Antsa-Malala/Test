package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Place;
import org.project.clouds5_backend.repository.CategorieRepository;
import org.project.clouds5_backend.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAllPlaces() {
        List<Place> places = placeRepository.findByEtatNot(10);
        if (places.isEmpty()) {
            return Collections.emptyList();
        }else {
            return places;
        }
    }

    public Place getPlaceById(Integer id) {
        Place place = placeRepository.findByIdPlaceAndEtatNot(id, 10);
        if(place == null) {
            return null;
        }
        return place;
    }

    public Place createPlace(Place place) {
        try{
            return placeRepository.save(place);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Place updatePlaceById(Integer id, Place place) {
        Optional<Place> optionalPlace = Optional.ofNullable(placeRepository.findByIdPlaceAndEtatNot(id, 10));
        if(optionalPlace.isPresent()){
            Place placeToUpdate = optionalPlace.get();
            placeToUpdate.setValeur(place.getValeur());
            placeRepository.save(placeToUpdate);
            return placeToUpdate;
        }else {
            throw new RuntimeException("Place non trouvee");
        }
    }

    public Place deletePlaceById(Integer id) {
        Optional<Place> optionalPlace = Optional.ofNullable(placeRepository.findByIdPlaceAndEtatNot(id, 10));
        if(optionalPlace.isPresent()){
            Place placeToDelete = optionalPlace.get();
            placeToDelete.setEtat(10);
            placeRepository.save(placeToDelete);
            return placeToDelete;
        }else {
            throw new RuntimeException("Place non trouvee");
        }
    }
}
