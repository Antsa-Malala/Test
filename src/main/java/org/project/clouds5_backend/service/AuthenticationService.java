package org.project.clouds5_backend.service;

import org.project.clouds5_backend.dao.request.SignUpRequest;
import org.project.clouds5_backend.dao.request.SigninRequest;
import org.project.clouds5_backend.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}

