package com.Toucomex.Importation_Toucomex.Models;


import com.sun.istack.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "produit_commande")
public class ProduitCommande {

    @EmbeddedId
    private ProduitCommandeId id;

    @ManyToOne
    //@MapsId("ID_pdt")
    @JoinColumn(name = "ID_pdt")
    private Produit produit;

    @ManyToOne
    //@MapsId("ID_cmd")
    @JoinColumn(name = "ID_cmd")
    private Commande commande;


    @ManyToOne
    //@MapsId("ID_facture")
    @Nullable
    @JoinColumn(name = "ID_f")
    private Facture facture;

    @Column(name = "produitQuantite")
    private Integer quantite;

    @Column(name = "produitprix")
    private Float prix;



    public ProduitCommande( Produit produit, Commande commande, Integer quantite, Float prix) {
        this.id = new ProduitCommandeId(commande.getID_cmd(),produit.getID_pdt());
        this.produit = produit;
        this.commande = commande;
        this.quantite = quantite;
        this.prix=prix;
    }


}
