package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Modele;
import org.project.clouds5_backend.model.Photo;
import org.project.clouds5_backend.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Photo sauvegarderPhoto(MultipartFile file, String idVoiture) throws IOException {
        try{
        Photo photo = new Photo();
        photo.setIdVoiture(idVoiture);
        photo.setPhoto(file.getBytes());
        return photoRepository.save(photo);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Photo> getPhotoByVoiture(String id) {
        List<Photo> photos = photoRepository.findByIdVoiture(id);
        if(photos.isEmpty()) {
            return Collections.emptyList();
        }
        return photos;
    }
}