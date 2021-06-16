package com.Toucomex.Importation_Toucomex.Controllers;

import com.Toucomex.Importation_Toucomex.Models.Commande;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Models.Produit;
import com.Toucomex.Importation_Toucomex.Models.Titre;
import com.Toucomex.Importation_Toucomex.Repositories.titreRepository;
import com.Toucomex.Importation_Toucomex.Services.TitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/titre")
public class TitreController {

    @Autowired
    TitreService ts;

    @Autowired
    titreRepository tr;

    @GetMapping("/titres")
    public ResponseEntity<List<Titre>> getAllTitres() { return ts.getalltitre(); }

    @PostMapping("/add")
    public Titre createTitre(@Valid @RequestBody Titre titre) {
        return tr.save(titre);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteTitre(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Titre t = tr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Titre introuvable avec l'id : " + id));

        tr.delete(t);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Supprimé", Boolean.TRUE);
        return response;
    }





}
