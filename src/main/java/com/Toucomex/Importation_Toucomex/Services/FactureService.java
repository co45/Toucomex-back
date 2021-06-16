package com.Toucomex.Importation_Toucomex.Services;

import com.Toucomex.Importation_Toucomex.Models.Commande;
import com.Toucomex.Importation_Toucomex.Models.ControleTech;
import com.Toucomex.Importation_Toucomex.Models.Facture;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FactureService {

@Autowired
private FactureRepository facrepos;


    public ResponseEntity<List<Facture>> getallfac()
    {
        List<Facture> lstfac=facrepos.findAll();
        if(lstfac.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(lstfac,HttpStatus.OK);
    }

    public ResponseEntity<Facture> addFacture(Facture f1) {
        try {
            List<Facture> lstfacture = findByNum_Fac(f1.getNum());
            if (!lstfacture.isEmpty())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            Facture _Facture = facrepos.save(f1);
            return new ResponseEntity<>(_Facture, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private List<Facture> findByNum_Fac(String Num_Fac) {
        return facrepos.findAll().stream()
                .filter(x -> x.getNum().equalsIgnoreCase(Num_Fac))
                .collect(Collectors.toList());
    }
    private ResponseEntity<Facture> findByNumf(String num) {
    Facture f=facrepos.getByfnum(num);
        if (f!=null) {
            return new ResponseEntity(f, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getFactureById(Long ID_f) {
        Optional<Facture> FactureData = facrepos.findById(ID_f);

        if (FactureData.isPresent()) {
            return new ResponseEntity(FactureData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity deleteFacture(Long ID_f)
    {
        Optional<Facture> result= facrepos.findById(ID_f);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        facrepos.deleteById(ID_f);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }



}
