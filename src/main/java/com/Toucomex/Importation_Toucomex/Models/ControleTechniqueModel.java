package com.Toucomex.Importation_Toucomex.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControleTechniqueModel {

    private String facture;
    private String numIncm;
    private String numLot;
    private String numApe;
    private String origine;
    private String provenance;
    private String quantite;
    private String produit;
    private LocalDate dateAmc;
    private LocalDate dateApe;
    private LocalDate dateIncm;
    private LocalDate datePeremption;
    private LocalDate dateRecEch;
}
