package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Mobile;
import org.project.clouds5_backend.repository.MessageRepository;
import org.project.clouds5_backend.repository.MobileRepository;
import org.springframework.stereotype.Service;


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
                throw new Exception("Token deja present");
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean check(Mobile mobile) throws Exception{
        try{
            if(mobileRepository.findByIdutilisateurAndToken(mobile.getIdUtilisateur(), mobile.getToken())!=null)
            {
                return false;
            }
            return true;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
