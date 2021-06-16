package com.Toucomex.Importation_Toucomex.Controllers;


import com.Toucomex.Importation_Toucomex.Auth.message.response.ResponseMessage;
import com.Toucomex.Importation_Toucomex.Auth.model.User;
import com.Toucomex.Importation_Toucomex.Models.Facture;
import com.Toucomex.Importation_Toucomex.Models.Produit;
import com.Toucomex.Importation_Toucomex.Models.ProduitCommande;
import com.Toucomex.Importation_Toucomex.Repositories.FactureRepository;
import com.Toucomex.Importation_Toucomex.Repositories.ProduitRepository;
import com.Toucomex.Importation_Toucomex.Repositories.listProduitCommandeRepository;
import com.Toucomex.Importation_Toucomex.Services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/facture")
public class FactureController {

    @Autowired
    FactureService fs;

    @Autowired
    FactureRepository fr;

    @Autowired
    ProduitRepository pr;

    @Autowired
    listProduitCommandeRepository lpr;

    @GetMapping("/all")
    public ResponseEntity<List<Facture>> getfs() {
        return fs.getallfac();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFac(@Valid @RequestBody Facture f) {
        if (fr.existsByNum(f.getNum())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Facture existe déja!"),
                    HttpStatus.BAD_REQUEST);
        }

        Facture fac = new Facture(f.getNum(), f.getDate_f(), f.getLibelle_f());
        Set<ProduitCommande> pfset = new HashSet<>();
        Set<Produit> sp = new HashSet<>();
        Set<String> produits = new HashSet<>();

        produits.forEach(p -> {
            ProduitCommande pf = new ProduitCommande();
            pf.setFacture(fac);
            pf.setProduit(pr.findProduitsByReference(p));
            pfset.add(pf);

        });
        fac.setProduitCommandes(pfset);
        fr.save(fac);
        lpr.saveAll(pfset);

        return new ResponseEntity<>(new ResponseMessage("Facture enregistré avec success !"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deletefacture(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Facture f = fr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fature de id : " + id + " n'existe pas"));

        fr.delete(f);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Supprimé", Boolean.TRUE);
        return response;
    }
}
