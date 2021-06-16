package com.Toucomex.Importation_Toucomex.Services;

import com.Toucomex.Importation_Toucomex.Models.Commande;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Models.Produit;
import com.Toucomex.Importation_Toucomex.Models.Titre;
import com.Toucomex.Importation_Toucomex.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitrepos;

    public ProduitService(ProduitRepository produitrepos) {
        this.produitrepos = produitrepos;
    }
    public ResponseEntity<List<Produit>> getallPdts()
    {
        List<Produit> lstpdt=produitrepos.findAll();
        if(lstpdt.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(lstpdt,HttpStatus.OK);
    }
    public Collection<Produit> getALlProduit() {
        return produitrepos.findAll();
    }

    public ResponseEntity<Produit> addProduit(Produit p1) {
        try {
            List<Produit> lstproduit = findByRef_pdt(p1.getReference());
            if (!lstproduit.isEmpty())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            Produit _Produit = produitrepos.save(p1);
            return new ResponseEntity<Produit>(_Produit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Produit> findByRef_pdt(String Ref_pdt) {
        return produitrepos.findAll().stream()
                .filter(x -> x.getReference().equalsIgnoreCase(Ref_pdt))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getProduitById(Long ID_pdt) {
        Optional<Produit> ProduitData = produitrepos.findById(ID_pdt);

        if (ProduitData.isPresent()) {
            return new ResponseEntity(ProduitData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public Produit findByNamep(String ref) {

        return produitrepos.findbynamepdtp(ref);
    }
    public Produit findByName(String ref) {

        return produitrepos.findbynamepdt(ref);
    }

    public ResponseEntity deleteProduit(Long ID_pdt) {
        Optional<Produit> result = produitrepos.findById(ID_pdt);
        if (result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        produitrepos.deleteById(ID_pdt);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
