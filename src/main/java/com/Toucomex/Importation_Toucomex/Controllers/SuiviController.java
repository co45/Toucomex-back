package com.Toucomex.Importation_Toucomex.Controllers;

import com.Toucomex.Importation_Toucomex.Auth.model.User;
import com.Toucomex.Importation_Toucomex.Auth.repository.UserRepository;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Models.SuiviImp;
import com.Toucomex.Importation_Toucomex.Repositories.ReceptionRepository;
import com.Toucomex.Importation_Toucomex.Repositories.SuiviImpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/suivi")
public class SuiviController {

    @Autowired
    private SuiviImpRepository sr;
    @Autowired
    private ReceptionRepository rr;

    @GetMapping("/suivis")
    public List<SuiviImp> getAllSuivis() {
        return sr.findAll();
    }

    @GetMapping("/suivi/{id}")
    public ResponseEntity<SuiviImp> getSuiviById(@PathVariable Long id) {
        SuiviImp s = sr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suivi does not exist with id :" + id));
        return ResponseEntity.ok(s);
    }
    @DeleteMapping("/suivi/{id}")
    public Map<String, Boolean> deleteSuivi(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        SuiviImp suivi = sr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suivi not found for this id :: " + id));

        sr.delete(suivi);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @PostMapping("/add")
    public ResponseEntity<SuiviImp> createSuivi(@RequestBody SuiviImp suivi) {
        try {
            SuiviImp s = sr.save(suivi);
            return new ResponseEntity<>(s, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
