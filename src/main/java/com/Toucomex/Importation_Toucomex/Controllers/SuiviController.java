package com.Toucomex.Importation_Toucomex.Controllers;

import com.Toucomex.Importation_Toucomex.Auth.model.User;
import com.Toucomex.Importation_Toucomex.Auth.repository.UserRepository;
import com.Toucomex.Importation_Toucomex.Models.*;
import com.Toucomex.Importation_Toucomex.Repositories.FactureRepository;
import com.Toucomex.Importation_Toucomex.Repositories.ReceptionRepository;
import com.Toucomex.Importation_Toucomex.Repositories.SuiviImpRepository;
import com.Toucomex.Importation_Toucomex.Repositories.titreRepository;
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
    @Autowired
    private FactureRepository fr;
    @Autowired
    private titreRepository tr;

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
    public ResponseEntity<SuiviImp> createSuivi(@Valid @RequestBody SuiviModel suivi) {



            SuiviImp s = new SuiviImp();
            s.setDate_arrive_stock(suivi.getDatearrivestock());
            System.out.println("========= DATE ARRIVE STOCK : "+ suivi.getDatearrivestock());

            s.setFac(fr.getByfnum(suivi.getFacture()));
            s.setDate_declaration(suivi.getDatedeclaration());
            s.setNum_declaration(suivi.getNumdeclaration());
            s.setObservation(suivi.getObservation());
            s.setDate_arrive_f_p(suivi.getDatearrivefp());
            s.setShipment(suivi.getShipment());
            Reception rec = new Reception();
            rec.setDate_rcp(suivi.getDatereception());
            rec.setNum_rcp(suivi.getReception());
            rr.save(rec);
            s.setReceptionS(rec);
        try {
            System.out.println("************************** SUCCESS NEW SUIVI **************************");
            sr.save(s);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
