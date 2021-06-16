package com.Toucomex.Importation_Toucomex.Controllers;
import com.Toucomex.Importation_Toucomex.Auth.message.response.ResponseMessage;
import com.Toucomex.Importation_Toucomex.Models.ControleTech;
import com.Toucomex.Importation_Toucomex.Models.Facture;
import com.Toucomex.Importation_Toucomex.Services.CommandeService;
import com.Toucomex.Importation_Toucomex.Services.ControleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/controle")
public class ControleTechController {

    @Autowired
    private ControleService cs;


    @GetMapping("/all")
    public ResponseEntity<List<ControleTech>> getCtrl()
    {
        return  cs.getAllControletech();
    }
    @GetMapping("/ctrl/{id}")
    public ResponseEntity<ControleTech> getCtrl(@PathVariable Long id)
    {
        return cs.getControletechById(id);
    }
    @PostMapping("/newsCtrl")
    public ResponseEntity<?>addCtrl(@Valid @RequestBody ControleTech ctrl, Facture facture)
    {

//        Facture fac=new Facture(facture.getDate_f(),facture.getNum_Fac(), facture.getLibelle_f(),facture.getCommandeF());
//        ControleTech ctrlA=new ControleTech(ctrl.getQuantite(),ctrl.getProvenance(),ctrl.getOrigine(),ctrl.getNum_lot(),ctrl.getNum_incm(), ctrl.getNum_ape(),ctrl.getDate_recup_ech(),ctrl.getDate_per(),ctrl.getDate_dep_incm(),ctrl.getDate_ape(),ctrl.getDate_amc());
//
        return new ResponseEntity<>(HttpStatus.OK);//("User registered successfully!"), HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCtrl(@PathVariable Long id)
    {
        return cs.deleteControletech(id);
    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity<ControleTech> updateCrtl(@PathVariable Long id, @Valid @RequestBody ControleTech ctrl)
//    {
//        return ctrls.updateCtrl(id,ctrl);
//    }
}
