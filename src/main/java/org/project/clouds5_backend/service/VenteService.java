package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Vente;
import org.project.clouds5_backend.repository.VenteRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VenteService {
    private final VenteRepository venteRepository;

    public VenteService(VenteRepository venteRepository) {
        this.venteRepository = venteRepository;
    }

    public List<Vente> getAllVentes() {
        List<Vente> ventes = venteRepository.findAll();
        if(ventes.isEmpty()){
            return Collections.emptyList();
        }else{
            return ventes;
        }
    }

    public Vente getVenteById(String id) {
        Vente vente = venteRepository.findById(id).orElse(null);
        if(vente == null) {
            return null;
        }
        return vente;
    }

    public Vente createVente(Vente vente) {
        try{
            return venteRepository.save(vente);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Vente updateVenteById(String id, Vente vente) {
        Optional<Vente> optionalVente = Optional.ofNullable(venteRepository.findById(id).orElse(null));
        if (optionalVente.isPresent()) {
            Vente venteToUpdate = optionalVente.get();
            venteToUpdate.setAnnonce(vente.getAnnonce());
            venteToUpdate.setDateVente(vente.getDateVente());
            venteRepository.save(venteToUpdate);
            return venteToUpdate;
        } else {
            throw new RuntimeException("Vente non trouvee");
        }
    }

    public Vente deleteVenteById(String id) {
        Optional<Vente> optionalVente = Optional.ofNullable(venteRepository.findById(id).orElse(null));
        if(optionalVente.isPresent()){
            Vente venteToDelete = optionalVente.get();
            venteRepository.delete(venteToDelete);
            return venteToDelete;
        }else{
            throw new RuntimeException("Vente non trouvee");
        }
    }

    public String getNextValSequence()
    {
        return venteRepository.getNextValSequence();
    }
}
