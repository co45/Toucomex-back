package com.Toucomex.Importation_Toucomex.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facture")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_f;

    private String num;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_f;

    private String Libelle_f;

    @NonNull
    @CreationTimestamp
    private Date dateCreation;

    @NonNull
    @UpdateTimestamp
    private Date dateUpdate;


    @OneToOne(cascade=CascadeType.ALL)
    private SuiviImp FacSuivi;



    @OneToOne(mappedBy = "facCtrl")
    private ControleTech ctrl;

    @OneToMany(mappedBy = "facture")
    private Set<ProduitCommande> ProduitCommandes = new HashSet<>();

    public Facture(String num_fac, LocalDate date_f, String libelle_f) {
        this.num = num_fac;
        Date_f = date_f;
        Libelle_f = libelle_f;


    }


}

