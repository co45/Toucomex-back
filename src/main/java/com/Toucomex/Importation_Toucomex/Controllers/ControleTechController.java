package com.Toucomex.Importation_Toucomex.Controllers;
import com.Toucomex.Importation_Toucomex.Auth.message.response.ResponseMessage;
import com.Toucomex.Importation_Toucomex.Models.ControleTech;
import com.Toucomex.Importation_Toucomex.Models.ControleTechniqueModel;
import com.Toucomex.Importation_Toucomex.Models.Facture;
import com.Toucomex.Importation_Toucomex.Models.Produit;
import com.Toucomex.Importation_Toucomex.Repositories.FactureRepository;
import com.Toucomex.Importation_Toucomex.Repositories.controleRepository;
import com.Toucomex.Importation_Toucomex.Services.CommandeService;
import com.Toucomex.Importation_Toucomex.Services.ControleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/controle")
public class ControleTechController {

    @Autowired
    private ControleService cs;
    @Autowired
    private controleRepository cr;
    @Autowired
    private FactureRepository fr;



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
    public ResponseEntity<?>addCtrl(@Valid @RequestBody ControleTechniqueModel ctrl)
    {

        LocalDate dc = LocalDate.now();
        LocalDate du = dc;
        Produit p = new Produit();
        ControleTech rctrl=new ControleTech(ctrl.getNumLot(),ctrl.getDatePeremption(),ctrl.getNumIncm(),ctrl.getDateIncm(),ctrl.getDateAmc(),ctrl.getDateRecEch(),ctrl.getNumApe(),ctrl.getDateApe(),Integer.parseInt(ctrl.getQuantite()),ctrl.getProvenance(),ctrl.getOrigine(),fr.getByfnum(ctrl.getFacture()));



        return new ResponseEntity<>(HttpStatus.OK);//("User registered successfully!"), HttpStatus.OK);

    }

    @GetMapping("/try")
    public String addCtrl()
    {
        ControleTech ctr= new ControleTech();
        ctr.setNum_lot("1235");
        ctr.setQuantite(10);
        ctr.setNum_incm("12365");
        Facture facture= new Facture();
        facture.setNum("646231");
        ctr.setFacCtrl(facture);
        try {
            cr.save(ctr);
            return "200";
        }catch(Exception x){
            return "400";

        }
    }

    @GetMapping("/tryread")
    public String test()
    {
        try {
            ControleTech ctrl= cr.findById(Long.valueOf(3)).get();
            System.out.println("TESTXXX : "+ ctrl.toString());
            System.out.println("TESTRRRR : "+ ctrl.getFacCtrl().getID_f());
            return "200";
        }catch(Exception x){
            return "400";

        }
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
