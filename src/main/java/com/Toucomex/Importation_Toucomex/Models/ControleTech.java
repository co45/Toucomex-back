package com.Toucomex.Importation_Toucomex.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ControleTech")
public class ControleTech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_ctrl;

    private String Num_lot;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_per;

    private String Num_incm;

    private String produits;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_dep_incm;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_amc;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_recup_ech;

    private String Num_ape;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_ape;

    private int Quantite;

    private String Provenance;

    private String Origine;

    @CreationTimestamp
    private Date dateCreation;

    @UpdateTimestamp
    private Date dateUpdate;

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name ="fac_id")
    private Facture facCtrl;


    public ControleTech(String num_lot, LocalDate date_per, String num_incm, LocalDate date_dep_incm, LocalDate date_amc, LocalDate date_recup_ech, String num_ape, LocalDate date_ape, int quantite, String provenance, String origine, Facture facCtrl) {
        Num_lot = num_lot;
        Date_per = date_per;
        Num_incm = num_incm;
        Date_dep_incm = date_dep_incm;
        Date_amc = date_amc;
        Date_recup_ech = date_recup_ech;
        Num_ape = num_ape;
        Date_ape = date_ape;
        Quantite = quantite;
        Provenance = provenance;
        Origine = origine;
        this.facCtrl = facCtrl;
    }

}
