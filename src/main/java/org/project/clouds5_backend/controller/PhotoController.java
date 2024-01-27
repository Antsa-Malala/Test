package org.project.clouds5_backend.controller;

import org.project.clouds5_backend.model.*;
import org.project.clouds5_backend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("photo")
public class PhotoController {
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Reponse<Photo>> sauvegarderPhoto(@RequestParam("photo") MultipartFile photo, @RequestParam("idVoiture") String idVoiture) throws IOException {
        Reponse<Photo> reponse = new Reponse<>();
        try{
            Photo pic = photoService.sauvegarderPhoto(photo,idVoiture);
            if(pic != null){
                reponse.setData(pic);
                reponse.setRemarque("Photo uploadé");
                return ResponseEntity.status(201).body(reponse);
            }else{
                reponse.setErreur("Photo non uploadé");
                return ResponseEntity.status(400).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reponse<List<Photo>>> getByVoiture(@PathVariable String id) {
        Reponse<List<Photo>> reponse = new Reponse<>();
        try {
            List<Photo> photos = photoService.getPhotoByVoiture(id);
            if (!photos.isEmpty()) {
                reponse.setData(photos);
                reponse.setRemarque("Liste des photos");
                return ResponseEntity.ok().body(reponse);
            } else {
                reponse.setErreur("Liste vide");
                return ResponseEntity.status(404).body(reponse);
            }
        }catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

    @PostMapping("/upload/online")
    public ResponseEntity<Reponse<List<JsonResponse>>> uploadFileOnline(@RequestBody FilesBody files) {
        Reponse<List<JsonResponse>> reponse = new Reponse<>();
        System.out.println("bik");
        try {
            List<JsonResponse> a=new ArrayList<>();
            for (String fileBase64 : files.getFiles()) {
                a.add(photoService.uploadOnline(fileBase64,files.getIdVoiture()));
            }
            if(!a.isEmpty()) {
                reponse.setData(a);
                reponse.setRemarque("Photo uploadé");
                return ResponseEntity.status(201).body(reponse);
            }
            else{
                reponse.setErreur("Photo non uploadé");
                return ResponseEntity.status(400).body(reponse);
            }

        } catch (Exception e) {
            reponse.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(reponse);
        }
    }

}