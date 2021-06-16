package com.Toucomex.Importation_Toucomex.Models;


import lombok.*;

import javax.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_pdt;

    @NonNull
    private String reference;

    @NonNull
    private String Libelle_pdt;

    @NonNull
    private String Famille;

    @NonNull
    private String gamme;

    @NonNull
    private String therapie;

    @NonNull
    private String nom_commercial;

    @NonNull
    private String code_ngp;

    @NonNull
    private String designation;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private Set<ProduitCommande> ProduitCommandes = new HashSet<>();












}
