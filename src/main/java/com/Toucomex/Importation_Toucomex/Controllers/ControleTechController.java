package com.Toucomex.Importation_Toucomex.Controllers;

import com.Toucomex.Importation_Toucomex.Auth.message.response.ResponseMessage;
import com.Toucomex.Importation_Toucomex.Models.*;
import com.Toucomex.Importation_Toucomex.Repositories.FactureRepository;
import com.Toucomex.Importation_Toucomex.Repositories.ProduitRepository;
import com.Toucomex.Importation_Toucomex.Repositories.controleRepository;
import com.Toucomex.Importation_Toucomex.Services.CommandeService;
import com.Toucomex.Importation_Toucomex.Services.ControleService;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/controle")
public class ControleTechController {

    @Autowired
    private ControleService cs;
    @Autowired
    private controleRepository cr;
    @Autowired
    private FactureRepository fr;

    @Autowired
    private ProduitRepository pr;


    /*@GetMapping("/all")
    public ResponseEntity<List<ControleTech>> getCtrl() {
        return cs.getAllControletech();
    }*/

    @GetMapping("/all")
    public ResponseEntity<List<ControlTechTableModel>> getCtrl() {

        List<ControlTechTableModel> cscs = new ArrayList<>();
        for (ControleTech tmp : cs.getAllControletech().getBody()) {
            String[] products = tmp.getProduits().split(",");

            for (Produit proEn : pr.findAll()) {
                for (String prod : products) {
                    if(proEn.getID_pdt() == Long.valueOf(prod)) {
                        ControlTechTableModel model = new ControlTechTableModel();
                        model.setControlTech(tmp.getID_ctrl()+"");
                        model.setRefProuct(""+proEn.getID_pdt());
                        model.setFacture(tmp.getFacCtrl().getNum());
                        cscs.add(model);
                    }
                }
            }

        }

        System.out.println("SIZE MODEL: "+cscs.size());
        int count = 0;
        for (ControlTechTableModel mmm : cscs) {
            count++;
            System.out.println("model: "+count+" ===> " +mmm.toString());
        }


        return new ResponseEntity<>(cscs, HttpStatus.OK);
    }


    @GetMapping("/ctrl/{id}")
    public ResponseEntity<ControleTech> getCtrl(@PathVariable Long id) {
        return cs.getControletechById(id);
    }

    @PostMapping("/newsCtrl")
    public ResponseEntity<?> addCtrl(@Valid @RequestBody ControleTechniqueModel ctrl) {

        System.out.println("ControleTechniqueModel from addCtrl: "+ctrl.toString());
        ControleTech ctr = new ControleTech();
        ctr.setNum_lot(ctrl.getNumLot());
        ctr.setProduits(ctrl.getProduit().substring(0, ctrl.getProduit().length() - 1) );
        Facture facture = new Facture();
        facture.setNum(ctrl.getFacture());
        ctr.setFacCtrl(facture);
        try{
            cr.save(ctr);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/try")
    public String addCtrl() {
        ControleTech ctr = new ControleTech();
        ctr.setNum_lot("1235");
        ctr.setQuantite(10);
        ctr.setNum_incm("12365");
        Facture facture = new Facture();
        facture.setNum("646231");
        ctr.setFacCtrl(facture);
        try {
            cr.save(ctr);
            return "200";
        } catch (Exception x) {
            return "400";

        }
    }

    @GetMapping("/tryread")
    public String test() {
       /* try {
            ControleTech ctrl = cr.findById(Long.valueOf(3)).get();
            System.out.println("TESTXXX : " + ctrl.toString());
            System.out.println("TESTRRRR : " + ctrl.getFacCtrl().getID_f());
            return "200";
        } catch (Exception x) {
            return "400";

        }*/

        List<ControlTechTableModel> cscs = new ArrayList<>();
        for (ControleTech tmp : cs.getAllControletech().getBody()) {
            String[] products = tmp.getProduits().split(",");

            for (Produit proEn : pr.findAll()) {
                for (String prod : products) {
                    if(proEn.getID_pdt() == Long.valueOf(prod)) {
                        ControlTechTableModel model = new ControlTechTableModel();
                        model.setControlTech(tmp.getID_ctrl()+"");
                        model.setRefProuct(""+proEn.getID_pdt());
                        model.setFacture(tmp.getFacCtrl().getNum());
                        cscs.add(model);
                    }
                }
            }

        }

        System.out.println("SIZE MODEL: "+cscs.size());
        int count = 0;
        for (ControlTechTableModel mmm : cscs) {
            count++;
            System.out.println("model: "+count+" ===> " +mmm.toString());
        }
        return "200";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCtrl(@PathVariable Long id) {
        return cs.deleteControletech(id);
    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity<ControleTech> updateCrtl(@PathVariable Long id, @Valid @RequestBody ControleTech ctrl)
//    {
//        return ctrls.updateCtrl(id,ctrl);
//    }
}
