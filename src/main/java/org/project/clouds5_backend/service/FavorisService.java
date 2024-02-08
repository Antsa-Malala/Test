package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Favoris;
import org.project.clouds5_backend.model.Annonce;
import org.project.clouds5_backend.model.Photo;
import org.project.clouds5_backend.repository.FavorisRepository;
import org.springframework.stereotype.Service;
import org.project.clouds5_backend.model.Utilisateur;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class FavorisService {
    private final FavorisRepository favorisRepository;
    private final PhotoService photoService;

    public FavorisService(FavorisRepository favorisRepository, PhotoService photoService) {
        this.favorisRepository = favorisRepository;
        this.photoService=photoService;
    }

    public List<Favoris> getAllFavoris() {
        List<Favoris> favoris = favorisRepository.findAll();
        if(favoris.isEmpty()){
            return Collections.emptyList();
        }else{
            return favoris;
        }
    }

    public Favoris getFavorisById(String id) {
        Favoris favoris = favorisRepository.findById(id).orElse(null);
        if(favoris == null) {
            return null;
        }
        return favoris;
    }

    public Favoris createFavoris(Favoris favoris) {
        try{
            String idFavoris=favorisRepository.getNextValSequence();
            favoris.setIdFavoris(idFavoris);
            return favorisRepository.save(favoris);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Favoris updateFavorisById(String id, Favoris favoris) {
        Favoris favorisToUpdate = favorisRepository.findById(id).orElse(null);
        if (favorisToUpdate != null) {
            favorisToUpdate.setUtilisateur(favoris.getUtilisateur());
            favorisToUpdate.setAnnonce(favoris.getAnnonce());
            favorisRepository.save(favorisToUpdate);
            return favorisToUpdate;
        } else {
            throw new RuntimeException("Favoris non trouvee");
        }
    }

    public Favoris deleteFavorisById(String id) {
        Favoris favorisToDelete = favorisRepository.findById(id).orElse(null);
        if(favorisToDelete != null){
            favorisRepository.delete(favorisToDelete);
            return favorisToDelete;
        }else{
            throw new RuntimeException("Favoris non trouvee");
        }
    }

    public List<Favoris> getFavorisByUser(Utilisateur utilisateur){
        List<Favoris> lf= favorisRepository.findByUtilisateur(utilisateur);
        if (lf.isEmpty()) {
            return Collections.emptyList();
        } else {
            for (Favoris f : lf) {
                this.setPhoto(f.getAnnonce());
            }
            return lf;
        }
    }

    public void setPhoto(Annonce a) {
        List<Photo> liste = photoService.getPhotoByVoiture(a.getVoiture().getIdVoiture());
        Photo[] sary = new Photo[liste.size()];
        a.setPhoto(liste.toArray(sary));
    }
}
