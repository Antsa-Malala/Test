package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Porte;
import org.project.clouds5_backend.repository.PorteRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PorteService {
    private final PorteRepository porteRepository;

    public PorteService(PorteRepository porteRepository) {
        this.porteRepository = porteRepository;
    }

    public List<Porte> getAllPortes() {
        List<Porte> portes = porteRepository.findByEtatNot(10);
        if (portes.isEmpty()) {
            return Collections.emptyList();
        }else {
            return portes;
        }
    }

    public Porte getPorteById(Integer id) {
        Porte porte = porteRepository.findByIdPorteAndEtatNot(id, 10);
        if(porte == null) {
            return null;
        }
        return porte;
    }

    public Porte createPorte(Porte porte) {
        try{
            return porteRepository.save(porte);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Porte updatePorteById(Integer id, Porte porte) {
        Optional<Porte> optionalPorte = Optional.ofNullable(porteRepository.findByIdPorteAndEtatNot(id, 10));
        if(optionalPorte.isPresent()){
            Porte porteToUpdate = optionalPorte.get();
            porteToUpdate.setValeur(porte.getValeur());
            porteRepository.save(porteToUpdate);
            return porteToUpdate;
        }else {
            throw new RuntimeException("Porte non trouvee");
        }
    }

    public Porte deletePorteById(Integer id) {
        Optional<Porte> optionalPorte = Optional.ofNullable(porteRepository.findByIdPorteAndEtatNot(id, 10));
        if(optionalPorte.isPresent()){
            Porte porteToDelete = optionalPorte.get();
            porteToDelete.setEtat(10);
            porteRepository.save(porteToDelete);
            return porteToDelete;
        }else {
            throw new RuntimeException("Porte non trouvee");
        }
    }
}
