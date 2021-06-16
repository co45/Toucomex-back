package com.Toucomex.Importation_Toucomex.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_cmd;

    @NonNull
    private String numero;

    @NonNull
    private LocalDate Date_cmd;

    private String Libelle_cmd;

    @NonNull
    @CreationTimestamp
    private Date dateCreation;

    @NonNull
    @UpdateTimestamp
    private Date dateUpdate;

    @OneToMany(mappedBy = "commande")
    private Set<ProduitCommande> ProduitCommandes = new HashSet<>();


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name ="ID_fsr")
    private Fournisseur fsrc;







}
