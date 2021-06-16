package com.Toucomex.Importation_Toucomex.Models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProduitCommandeId implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commande_id")
    private Long commandeId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produit_id")
    private Long produitId;



}
