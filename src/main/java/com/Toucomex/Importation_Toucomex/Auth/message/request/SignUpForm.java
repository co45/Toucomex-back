package com.Toucomex.Importation_Toucomex.Auth.message.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Data

public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min=3, max = 50)
    private String prenom;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;


    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Date dateOfBirth;

    private String phone;

    private byte[] photo;

    private Set<String> role;

    private String departement;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


}