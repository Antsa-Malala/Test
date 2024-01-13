package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Boite;
import org.project.clouds5_backend.repository.BoiteRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BoiteService {
    private final BoiteRepository boiteRepository;

    public BoiteService(BoiteRepository boiteRepository) {
        this.boiteRepository = boiteRepository;
    }

    public List<Boite> getAllBoites() {
        List<Boite> boites = boiteRepository.findByEtatNot(10);
        if(boites.isEmpty()) {
            return Collections.emptyList();
        }else {
            return boites;
        }
    }

    public Boite getBoiteById(Integer id) {
        Boite boite = boiteRepository.findByIdBoiteAndEtatNot(id, 10);
        if(boite == null) {
            return null;
        }
        return boite;
    }

    public Boite createBoite(Boite boite) {
        try{
            return boiteRepository.save(boite);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Boite updateBoiteById(Integer id, Boite boite) {
        Optional<Boite> optionalBoite = Optional.ofNullable(boiteRepository.findByIdBoiteAndEtatNot(id, 10));
        if(optionalBoite.isPresent()){
            Boite boiteToUpdate = optionalBoite.get();
            boiteToUpdate.setNomBoite(boite.getNomBoite());
            boiteRepository.save(boiteToUpdate);
            return boiteToUpdate;
        }else {
            throw new RuntimeException("Boite non trouvee");
        }
    }

    public Boite deleteBoiteById(Integer id) {
        Optional<Boite> optionalBoite = Optional.ofNullable(boiteRepository.findByIdBoiteAndEtatNot(id, 10));
        if(optionalBoite.isPresent()){
            Boite boiteToDelete = optionalBoite.get();
            boiteToDelete.setEtat(10);
            boiteRepository.save(boiteToDelete);
            return boiteToDelete;
        }else {
            throw new RuntimeException("Boite non trouvee");
        }
    }
}
