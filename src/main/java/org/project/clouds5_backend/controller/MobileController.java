package org.project.clouds5_backend.controller;

import org.project.clouds5_backend.model.Mobile;
import org.project.clouds5_backend.model.Reponse;
import org.project.clouds5_backend.service.MobileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("mobiles")
@RestController
public class MobileController {
    private MobileService mobileService;

    public MobileController(MobileService mobileService) {
        this.mobileService = mobileService;
    }

    @PostMapping
    public ResponseEntity<Reponse<Mobile>> insert(@RequestBody Mobile mobile){
        Reponse<Mobile> valiny = new Reponse<>();
        try {
            Mobile m=mobileService.insert(mobile);
            if(m!=null)
            {
                valiny.setData(m);
                valiny.setRemarque("Token mobile inseree");
                return ResponseEntity.ok().body(valiny);
            }
            else{
                valiny.setErreur("Token mobile non inseree");
                return ResponseEntity.status(200).body(valiny);
            }
        } catch (Exception e){
            valiny.setErreur(e.getMessage());
            return ResponseEntity.status(500).body(valiny);
        }
    }
}
