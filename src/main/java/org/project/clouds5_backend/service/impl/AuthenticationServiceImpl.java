package org.project.clouds5_backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.clouds5_backend.dao.request.SignUpRequest;
import org.project.clouds5_backend.dao.request.SigninRequest;
import org.project.clouds5_backend.dao.response.JwtAuthenticationResponse;
import org.project.clouds5_backend.model.Utilisateur;
import org.project.clouds5_backend.model.Ville;
import org.project.clouds5_backend.repository.UtilisateurRepository;
import org.project.clouds5_backend.repository.VilleRepository;
import org.project.clouds5_backend.service.AuthenticationService;
import org.project.clouds5_backend.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UtilisateurRepository userRepository;
    private final VilleRepository villeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        Ville ville = villeRepository.findById(request.getId_ville())
                .orElseThrow(() -> new RuntimeException("Ville not found for id: " + request.getId_ville()));

        String idUtilisateur=userRepository.getNextValSequence();
        var user = Utilisateur.builder()
                .idUtilisateur(idUtilisateur)
                .prenom(request.getFirstName())
                .nom(request.getLastName())
                .ville(ville)
                .adresse(request.getAdresse())
                .contact(request.getContact())
                .mail(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole()).build();


        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = userRepository.findByMail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


}
