package org.project.clouds5_backend.service;

import org.project.clouds5_backend.entities.Json;
import org.project.clouds5_backend.entities.RequestAPI;
import org.project.clouds5_backend.model.JsonResponse;
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
    String uploadFolder = "./uploads";
    String API_KEY = "98f28e6748b1982ed2aabfd02b5cd0d6";
    String URL_Server_BB = "https://api.imgbb.com/1/upload";

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Photo sauvegarderPhoto(MultipartFile file, String idVoiture) throws IOException {
        try{
        Photo photo = new Photo();
        photo.setIdVoiture(idVoiture);
        //photo.setPhoto(file.getBytes());
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

    public JsonResponse uploadOnline(String base64Image,String idVoiture) {
        String key = "key=" + API_KEY;
        try {
            String res = RequestAPI.sendFormData(URL_Server_BB + "?" + key, base64Image);
            System.out.println(res);
            JsonResponse jsonResponse = (JsonResponse) Json.fromJson(res, JsonResponse.class);
            Photo photo = new Photo();
            photo.setIdVoiture(idVoiture);
            photo.setPhoto(jsonResponse.getData().getDisplay_url());
            photoRepository.save(photo);
            return jsonResponse;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}