package com.Toucomex.Importation_Toucomex.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControlTechTableModel {

    private String controlTech;
    private String facture;
    private String refProuct;
}



