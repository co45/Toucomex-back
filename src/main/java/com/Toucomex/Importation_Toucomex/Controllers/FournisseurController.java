package com.Toucomex.Importation_Toucomex.Controllers;

import com.Toucomex.Importation_Toucomex.Auth.model.User;
import com.Toucomex.Importation_Toucomex.Auth.repository.UserRepository;
import com.Toucomex.Importation_Toucomex.Models.ControleTech;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Models.Produit;
import com.Toucomex.Importation_Toucomex.Repositories.FournisseurRepository;
import com.Toucomex.Importation_Toucomex.Services.FournisseurService;
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
@RequestMapping("/fournisseur")
public class FournisseurController {

    @Autowired
    private FournisseurService fs;
    @Autowired
    private FournisseurRepository fr;


    @GetMapping("/all")
    public ResponseEntity<List<Fournisseur>> getFsr() {
        return fs.getallfournisseur();
    }

    @GetMapping("/fsr/{id}")
    public ResponseEntity<Fournisseur> getFsr(@PathVariable Long id)
    {
        return fs.getFournisseurById(id);
    }

    @PostMapping("/newfsr")
    public Fournisseur addFsr(@Valid @RequestBody Fournisseur fsr)
    {
        return fr.save(fsr);
    }

    @DeleteMapping("/fsr/{id}")
    public Map<String, Boolean> deleteFsr(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Fournisseur fsr = fr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur introuvable avec l'id : " + id));

        fr.delete(fsr);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @PutMapping("/fsr/{id}")
    public ResponseEntity<Fournisseur> updateFsr(@PathVariable Long id, @Valid @RequestBody Fournisseur ffr)
    {
        return fs.updateFournisseur(id,ffr);
    }
}
