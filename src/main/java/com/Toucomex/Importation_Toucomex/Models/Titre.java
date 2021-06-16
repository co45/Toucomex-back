package com.Toucomex.Importation_Toucomex.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "titre")
public class Titre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_t;

    private String Num_t;

    private String code;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate Date_t;

    private Double Montant;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name ="TitreFsr")
    private Fournisseur FournisseurTitre;

}
