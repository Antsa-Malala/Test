package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Message;
import org.project.clouds5_backend.model.Mobile;
import org.project.clouds5_backend.repository.MessageRepository;
import org.project.clouds5_backend.repository.MobileRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class MobileService {
    private MobileRepository mobileRepository;

    public MobileService(MobileRepository mobileRepository) {
        this.mobileRepository = mobileRepository;
    }

    //    Insert message
    public Mobile insert(Mobile mobile) throws Exception{
        try{
            if(this.check(mobile))
            {
                return mobileRepository.save(mobile);
            }
            else{
                return null;
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean check(Mobile mobile) throws Exception{
        try{
            if(mobileRepository.findByIdUtilisateurAndToken(mobile.getIdUtilisateur(), mobile.getToken())!=null)
            {
                return false;
            }
            return true;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Mobile> getAllToken(String idUser1){
        List<Mobile> mobiles = mobileRepository.findByIdUtilisateur(idUser1);
        if(mobiles.isEmpty())
        {
            return Collections.emptyList();
        }
        else{
            return mobiles;
        }
    }
}
