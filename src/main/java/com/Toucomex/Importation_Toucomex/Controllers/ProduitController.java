package com.Toucomex.Importation_Toucomex.Controllers;

import com.Toucomex.Importation_Toucomex.Models.*;
//import com.Toucomex.Importation_Toucomex.Repositories.ProduitRepository;
import com.Toucomex.Importation_Toucomex.Repositories.FactureRepository;
import com.Toucomex.Importation_Toucomex.Repositories.ProduitRepository;
import com.Toucomex.Importation_Toucomex.Repositories.listProduitCommandeRepository;
import com.Toucomex.Importation_Toucomex.Services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    ProduitRepository pr;
    @Autowired
    ProduitService ps;
    @Autowired
    listProduitCommandeRepository pcr;
    @Autowired
    FactureRepository fr;
    @PersistenceContext
    EntityManager em;


    @GetMapping("/pdts")
    public ResponseEntity<List<Produit>> getAllPdts() { return ps.getallPdts(); }


    @PostMapping("/createctrl/{ids}/{numfac}")
    public void createPdt(@PathVariable String ids, @PathVariable String numfac ) {
        //create facture
        Facture facture = new Facture();
        facture.setNum(numfac);
        fr.save(facture);

        //update facture id in ProduitCommande
        String[] lst = ids.split(",");


        for (String tmp : lst) {
            //pcr.updateCmd(facture.getID_f(), Long.parseLong(tmp));
//            updatecmdp(facture.getID_f(), Long.parseLong(tmp));


            Produit produit=null;
            for (Produit tmp1: pr.findAll() ) {
                if ((tmp1.getID_pdt()+"").equals(tmp)){
                    produit=tmp1;

                    break;
                }
            }
            System.out.println("produit :  "+produit.toString());

            ProduitCommande pdct=pcr.getCommandeProduitByPid(produit.getID_pdt().toString());
            System.out.println("ProduitCommande : "+ pdct);

            ProduitCommandeId pcid = new ProduitCommandeId();



            pcid.setProduitId(Long.parseLong(tmp));
//            pcid.setCommandeId();
//            pcr.findById()


        }
    }
    @Transactional
    public void updatecmdp(Long fid,Long pid){
        em.createNativeQuery("UPDATE TABLE produit_commande SET id_f="+fid+" WHERE produit_id="+pid).executeUpdate();

    }

    @GetMapping("/pdtss/{id}")
    public ResponseEntity<?> getProduitByIdCmd(@PathVariable("id") long id) {
        List<Produit> lstPrduitsByCmdId= new ArrayList<>();
        List<Integer> pdtCommandeId= new ArrayList<>();
        for(ProduitCommande tmp: pcr.findAll()){
            if(tmp.getId().getCommandeId() == id){
                pdtCommandeId.add(tmp.getId().getProduitId().intValue());
            }
        }
        System.out.println("IDs : "+ pdtCommandeId.toString());

        for(Produit tmp : getAllPdts().getBody()){
            System.out.println("ID : "+ tmp.getID_pdt().intValue());
            System.out.println("TUREorFALSE : "+ pdtCommandeId.contains(tmp.getID_pdt().intValue()) );
            if(pdtCommandeId.contains(tmp.getID_pdt().intValue())){
                lstPrduitsByCmdId.add(tmp);
            }
        }
        System.out.println("abc : "+lstPrduitsByCmdId.size());
        return new ResponseEntity<>(lstPrduitsByCmdId, HttpStatus.OK);
    }


    @GetMapping("/pdt/{id}")
    public ResponseEntity<?> getProduitById(@PathVariable("id") long id) {
        return ps.getProduitById(id);
    }

    @PostMapping("/new")
    public Produit createPdt(@Valid @RequestBody  Produit produit) {
        return pr.save(produit);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deletePdt(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Produit p = pr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable avec l'id : " + id));

        pr.delete(p);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Supprimé", Boolean.TRUE);
        return response;
    }

}
