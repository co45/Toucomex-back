package com.Toucomex.Importation_Toucomex.Services;

import com.Toucomex.Importation_Toucomex.Models.Commande;
import com.Toucomex.Importation_Toucomex.Models.ControleTech;
import com.Toucomex.Importation_Toucomex.Models.Produit;
import com.Toucomex.Importation_Toucomex.Repositories.controleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ControleService {
    @Autowired
    private controleRepository cntrlrepos ;
    @Autowired
    public  ControleService(controleRepository cntrlrepos){this.cntrlrepos = cntrlrepos;}

//    public ResponseEntity<ControleTech> addControle(ControleTech C1)
//    {
//
//       ControleTech controle =cntrlrepos.save(C1);
//        return new ResponseEntity<>(controle,HttpStatus.CREATED);
//
//    }
    private List<ControleTech> findByDate(Date dateCreation) {
        return cntrlrepos.findAll().stream()
                .filter(x -> x.getDateCreation().equals(dateCreation))
                .collect(Collectors.toList());
    }
    public ResponseEntity<List<ControleTech>> getAllControletech()
    {
    List<ControleTech> lst1=cntrlrepos.findAll();
    if (lst1.isEmpty())
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    return new ResponseEntity(lst1,HttpStatus.OK);

    }

    public ResponseEntity<ControleTech> getControletechById(Long ID_ctrl)
    {
        Optional<ControleTech> result= cntrlrepos.findById(ID_ctrl);
        if (result.isPresent()) {
            return new ResponseEntity(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity deleteControletech(Long ID_ctrl)
    {
        Optional<ControleTech> result= cntrlrepos.findById(ID_ctrl);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        cntrlrepos.deleteById(ID_ctrl);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
//    public ResponseEntity<ControleTech> updateCtrl(Long ID_ctrl, ControleTech newControletech)
//    {
//        Optional<ControleTech> result= cntrlrepos.findById(ID_ctrl);
//        if(result.isEmpty())
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        ControleTech controleTech= result.get();
//        controleTech.setNum_lot(newControletech.getNum_lot());
//        controleTech.setNum_incm(newControletech.getNum_incm());
//        controleTech.setDate_per(newControletech.getDate_per());
//        controleTech.setDate_amc(newControletech.getDate_amc());
//        controleTech.setDate_ape(newControletech.getDate_ape());
//        controleTech.setOrigine(newControletech.getOrigine());
//        controleTech.setProvenance(newControletech.getProvenance());
//        controleTech.setQuantite(newControletech.getQuantite());
//        controleTech.setNum_ape(newControletech.getNum_ape());
//        controleTech.setDate_recup_ech(newControletech.getDate_recup_ech());
//        controleTech.setDate_dep_incm(newControletech.getDate_dep_incm());
//        ControleTech controleTech1=cntrlrepos.save(controleTech);
//        return new ResponseEntity(controleTech,HttpStatus.ACCEPTED);
//    }
//    public ControleTech ctrlAdd(List<String> ref_pdt,LocalDate date_amc,LocalDate date_update,LocalDate date_creation,Integer quantite,String num_lot,String num_incm,Integer num_ape,LocalDate date_recup_ech,LocalDate date_per,LocalDate date_dep_incm,LocalDate date_ape){
//        ControleTech ctrl=new ControleTech();
//        ctrl.setDate_amc(date_amc);
//        ctrl.setDate_ape(date_ape);
//        ctrl.setDate_dep_incm(date_dep_incm);
//        ctrl.setDate_recup_ech(date_recup_ech);
//        ctrl.setNum_incm(num_incm);
//        ctrl.setNum_lot(num_lot);
//        ctrl.setQuantite(quantite);
//
//        for (String ref : ref_pdt) {
//            List<Produit> produits = null;
//            produits.add(pr.findbynamepdtp(ref));
//        }
//
//        return ctrl;
//
//
//    }
}
