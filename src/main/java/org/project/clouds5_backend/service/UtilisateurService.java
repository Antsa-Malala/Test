package org.project.clouds5_backend.service;

import jdk.jshell.execution.Util;
import org.project.clouds5_backend.model.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.project.clouds5_backend.repository.UtilisateurRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.project.clouds5_backend.model.Annonce;
import org.project.clouds5_backend.repository.UtilisateurRepository;
@Service
public class UtilisateurService implements UtilisateurServiceInter {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return utilisateurRepository.findByMail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateur = utilisateurRepository.findByEtatNotAndRoleNot(10,1);
        if(utilisateur.isEmpty()) {
            return Collections.emptyList();
        }else {
            return utilisateur;
        }
    }

    public Utilisateur getUtilisateurById(String id) {
        Utilisateur utilisateur = utilisateurRepository.findByIdUtilisateurAndEtatNot(id, 10);
        if(utilisateur == null) {
            return null;
        }
        return utilisateur;
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        try{
            utilisateur.setIdUtilisateur(utilisateur.getFullId());
            return utilisateurRepository.save(utilisateur);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Utilisateur updateUtilisateurById(String id, Utilisateur utilisateur) {
        Optional<Utilisateur> optionalUtilisateur = Optional.ofNullable(utilisateurRepository.findByIdUtilisateurAndEtatNot(id, 10));
        if(optionalUtilisateur.isPresent()){
            Utilisateur utilisateurToUpdate = optionalUtilisateur.get();
            utilisateurToUpdate.setNom(utilisateur.getNom());
            utilisateurToUpdate.setPrenom(utilisateur.getPrenom());
            utilisateurToUpdate.setVille(utilisateur.getVille());
            utilisateurToUpdate.setAdresse(utilisateur.getAdresse());
            utilisateurToUpdate.setContact(utilisateur.getContact());
            utilisateurToUpdate.setMail(utilisateur.getMail());
            utilisateurToUpdate.setMotDePasse(utilisateur.getMotDePasse());
            utilisateurToUpdate.setRole(utilisateur.getRole());
            utilisateurRepository.save(utilisateurToUpdate);
            return utilisateurToUpdate;
        }else {
            throw new RuntimeException("Utilisateur non trouvee");
        }
    }

    public Utilisateur deleteUtilisateurById(String id) {
        Optional<Utilisateur> optionalUtilisateur = Optional.ofNullable(utilisateurRepository.findByIdUtilisateurAndEtatNot(id, 10));
        if(optionalUtilisateur.isPresent()){
            Utilisateur utilisateurToDelete = optionalUtilisateur.get();
            utilisateurToDelete.setEtat(10);
            utilisateurRepository.save(utilisateurToDelete);
            return utilisateurToDelete;
        }else {
            throw new RuntimeException("Utilisateur non trouvee");
        }
    }

    public Utilisateur getConnected()
    {
        Utilisateur connecte=new Utilisateur();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {

            Object principal = authentication.getPrincipal();

            if (principal instanceof Utilisateur) {
                connecte = (Utilisateur) principal;
            }
        }
        return connecte;
    }
}