package com.Toucomex.Importation_Toucomex.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fournisseur")
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_fsr;

    @NonNull
    private String code_fsr;

    @NonNull
    private String nom;

    @NonNull
    private String adresse_fsr;

    @NonNull
    private String libelle_fsr;

    @NonNull
    private String swift;

    @JsonManagedReference
    @OneToMany(mappedBy="fsrc")
    private Set<Commande> commandeFournisseur= new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy="FournisseurTitre")
    private Set<Titre> fournisseurTitre;


}