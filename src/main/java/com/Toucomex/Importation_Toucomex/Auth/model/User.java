package com.Toucomex.Importation_Toucomex.Auth.model;
import com.Toucomex.Importation_Toucomex.Models.Facture;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.NaturalId;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min=3, max = 50)
    private String name;

    @Size(min=3, max = 50)
    private String prenom;

    @NonNull
    @Size(min=3, max = 50)
    private String username;

    @NaturalId
    @NonNull
    @Size(max = 50)
    @Email
    private String email;


    public String getPassword() {
        return password;
    }

    @NonNull
    @Size(min=6, max = 100)
    private String password;

    private Date dateOfBirth;
    private byte[] photo ;
    @Size(max= 8)
    private String phone;
    private String departement;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User( @NonNull String name,  String prenom, @NonNull String username, @NonNull String email, @NonNull String password, Date dateOfBirth,String phone ,String departement) {

        this.name = name;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.departement = departement;
        this.photo= photo;

    }

}