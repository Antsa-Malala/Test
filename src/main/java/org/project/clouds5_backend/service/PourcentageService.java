package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Commission;
import org.project.clouds5_backend.model.Porte;
import org.project.clouds5_backend.model.Pourcentage;
import org.project.clouds5_backend.repository.PlaceRepository;
import org.project.clouds5_backend.repository.PourcentageRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PourcentageService {
    private final PourcentageRepository pourcentageRepository;

    public PourcentageService(PourcentageRepository pourcentageRepository) {
        this.pourcentageRepository = pourcentageRepository;
    }

    public Pourcentage createPourcentage(Pourcentage pourcentage) {
        try{
            pourcentage.setDatePourcentage(new Date(System.currentTimeMillis()));
            return pourcentageRepository.save(pourcentage);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public double getValeur() {
        return pourcentageRepository.getValeur();
    }
}
