package com.Toucomex.Importation_Toucomex.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuiviModel {

    private String numdeclaration;
    private String observation;
    private String shipment;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate datearrivefp;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate datedeclaration;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate datearrivestock;
    private String facture;
    private String reception;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate datereception;
    private String montantfacture;
    private String titre;

}
