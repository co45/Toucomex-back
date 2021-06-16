package com.Toucomex.Importation_Toucomex.Services;

import com.Toucomex.Importation_Toucomex.Models.Commande;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Models.Titre;
import com.Toucomex.Importation_Toucomex.Repositories.titreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitreService {
    @Autowired
    private titreRepository titrerepos ;
    public  TitreService (titreRepository titrerepos) {this.titrerepos = titrerepos ;}

    public ResponseEntity<List<Titre>> getalltitre()
    {
        List<Titre> lsttitre=titrerepos.findAll();
        if(lsttitre.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(lsttitre,HttpStatus.OK);
    }

    public ResponseEntity<Titre> addTitre(Titre t1) {
        try {
            List<Titre> lsttitre = findByNum_t(t1.getNum_t());
            if (!lsttitre.isEmpty())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            Titre _Titre = titrerepos.save(t1);
            return new ResponseEntity<>(_Titre, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private List<Titre> findByNum_t(String Num_t ) {
        return titrerepos.findAll().stream()
                .filter(x -> x.getNum_t().equalsIgnoreCase(Num_t))
                .collect(Collectors.toList());
    }
    public ResponseEntity<?> getTitreById(Long ID_t) {
        Optional<Titre> TitreData = titrerepos.findById(ID_t);

        if (TitreData.isPresent()) {
            return new ResponseEntity(TitreData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity deleteTitre(Long ID_t)
    {
        Optional<Titre> result= titrerepos.findById(ID_t);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        titrerepos.deleteById(ID_t);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
