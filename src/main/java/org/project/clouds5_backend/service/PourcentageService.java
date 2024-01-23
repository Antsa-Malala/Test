package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Porte;
import org.project.clouds5_backend.model.Pourcentage;
import org.project.clouds5_backend.repository.PlaceRepository;
import org.project.clouds5_backend.repository.PourcentageRepository;
import org.springframework.stereotype.Service;

@Service
public class PourcentageService {
    private final PourcentageRepository pourcentageRepository;

    public PourcentageService(PourcentageRepository pourcentageRepository) {
        this.pourcentageRepository = pourcentageRepository;
    }

    public double getValeur() {
        return pourcentageRepository.getValeur();
    }
}
