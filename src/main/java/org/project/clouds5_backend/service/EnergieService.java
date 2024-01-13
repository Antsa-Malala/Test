package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Energie;
import org.project.clouds5_backend.repository.EnergieRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EnergieService {
    private final EnergieRepository energieRepository;

    public EnergieService(EnergieRepository energieRepository) {
        this.energieRepository = energieRepository;
    }

    public List<Energie> getAllEnergies() {
        List<Energie> energie = energieRepository.findByEtatNot(10);
        if(energie.isEmpty()) {
            return Collections.emptyList();
        }else {
            return energie;
        }
    }

    public Energie getEnergieById(Integer id) {
        Energie energie = energieRepository.findByIdEnergieAndEtatNot(id, 10);
        if(energie == null) {
            return null;
        }else {
            return energie;
        }
    }

    public Energie createEnergie(Energie energie) {
        try{
            return energieRepository.save(energie);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Energie updateEnergieById(Integer id, Energie energie) {
        Optional<Energie> optionalEnergie = Optional.ofNullable(energieRepository.findByIdEnergieAndEtatNot(id, 10));
        if(optionalEnergie.isPresent()){
            Energie energieToUpdate = optionalEnergie.get();
            energieToUpdate.setNomEnergie(energie.getNomEnergie());
            energieRepository.save(energieToUpdate);
            return energieToUpdate;
        }else {
            throw new RuntimeException("Energie non trouvee");
        }
    }

    public Energie deleteEnergieById(Integer id) {
        Optional<Energie> optionalEnergie = Optional.ofNullable(energieRepository.findByIdEnergieAndEtatNot(id, 10));
        if(optionalEnergie.isPresent()){
            Energie energieToDelete = optionalEnergie.get();
            energieToDelete.setEtat(10);
            return energieToDelete;
        }else {
            throw new RuntimeException("Energie non trouvee");
        }
    }
}
