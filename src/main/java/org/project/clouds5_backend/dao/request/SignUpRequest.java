package org.project.clouds5_backend.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private int id_ville;
    private String adresse;
    private String contact;
    private String email;
    private String password;
    private int role;
}
