package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Marque;
import org.project.clouds5_backend.repository.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarqueService {
    private final MarqueRepository marqueRepository;

    public MarqueService(MarqueRepository marqueRepository) {
        this.marqueRepository = marqueRepository;
    }

    public List<Marque> getAllMarques() {
        List<Marque> marques = marqueRepository.findByEtatNot(10);
        if(marques.isEmpty()){
            return null;
        }
        return marques;
    }

    public Marque getMarqueById(Integer id) {
        Marque marque = marqueRepository.findByIdMarqueAndEtatNot(id, 10);
        if(marque == null){
            return null;
        }
        return marque;
    }

    public Marque createMarque(Marque marque) {
        try{
            return marqueRepository.save(marque);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Marque updateMarqueById(Integer id, Marque marque) {
        Optional<Marque> optionalMarque = Optional.ofNullable(marqueRepository.findByIdMarqueAndEtatNot(id, 10));
        if(optionalMarque.isPresent()){
            Marque marqueToUpdate = optionalMarque.get();
            marqueToUpdate.setNomMarque(marque.getNomMarque());
            marqueRepository.save(marqueToUpdate);
            return marqueToUpdate;
        }else {
            throw new RuntimeException("Marque non trouvee");
        }
    }

    public Marque deleteMarqueById(Integer id) {
        Optional<Marque> optionalMarque = Optional.ofNullable(marqueRepository.findByIdMarqueAndEtatNot(id, 10));
        if(optionalMarque.isPresent()){
            Marque marqueToDelete = optionalMarque.get();
            marqueToDelete.setEtat(10);
            marqueRepository.save(marqueToDelete);
            return marqueToDelete;
        }else {
            throw new RuntimeException("Marque non trouvee");
        }
    }
}
