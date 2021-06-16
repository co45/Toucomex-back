package com.Toucomex.Importation_Toucomex.Services;

import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {
    @Autowired
    private FournisseurRepository fourrepos ;

    @Autowired
    public  FournisseurService (FournisseurRepository fourrepos) {this.fourrepos = fourrepos;}

    public ResponseEntity<List<Fournisseur>> getallfournisseur()
    {
        List<Fournisseur> lststds=fourrepos.findAll();
        if(lststds.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(lststds,HttpStatus.OK);
    }

    public ResponseEntity<Fournisseur> addFournisseur(Fournisseur F1) {
        try {
            Optional<Fournisseur> lstfournisseur = findByCodefr(F1.getCode_fsr());
            if (!lstfournisseur.isEmpty())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            Fournisseur _Fournisseur = fourrepos.save(F1);
            return new ResponseEntity<>(_Fournisseur, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Fournisseur findByName(String nom_fsr) {
        return fourrepos.findbynamefsr(nom_fsr);
    }

    public Optional<Fournisseur> findByCodefr(String code_fsr ) {
        return fourrepos.findAll().stream()
                .filter(x -> x.getCode_fsr().equalsIgnoreCase(code_fsr))
                .findFirst();
    }

    public ResponseEntity<Fournisseur> getFournisseurById(Long ID_fsr) {
        Optional<Fournisseur> FournisseurData = fourrepos.findById(ID_fsr);

        if (FournisseurData.isPresent()) {
            return new ResponseEntity(FournisseurData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteFournisseur(Long ID_fsr )
    {
        Optional<Fournisseur> result= fourrepos.findById(ID_fsr);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        fourrepos.deleteById(ID_fsr);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Fournisseur> updateFournisseur(Long id,Fournisseur f)
    {
        Optional<Fournisseur> res=fourrepos.findById(id);
        if(res.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Optional<Fournisseur> lst1= findByCodefr(f.getCode_fsr());
        if(lst1.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        Fournisseur ff=res.get();
        f.setNom(f.getNom());
        f.setAdresse_fsr(f.getAdresse_fsr());
        f.setLibelle_fsr(f.getLibelle_fsr());
        f.setSwift(f.getSwift());
        Fournisseur fr= fourrepos.save(f);
        return new ResponseEntity<>(fr,HttpStatus.OK);
    }


}