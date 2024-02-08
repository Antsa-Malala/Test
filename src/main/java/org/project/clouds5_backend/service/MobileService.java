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
            return mobileRepository.save(mobile);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
