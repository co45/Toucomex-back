package com.Toucomex.Importation_Toucomex.Services;

import com.Toucomex.Importation_Toucomex.Models.Produit;
import com.Toucomex.Importation_Toucomex.Models.Reception;
import com.Toucomex.Importation_Toucomex.Repositories.ProduitRepository;
import com.Toucomex.Importation_Toucomex.Repositories.ReceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceptionService {
    @Autowired
    private ReceptionRepository receptionrepos ;
    public ReceptionService(ReceptionRepository receptionrepos){this.receptionrepos = receptionrepos;}
    public Collection<Reception> getALlreception()
    {return receptionrepos.findAll(); }
    public ResponseEntity<Reception> addreception(Reception r1)
    { try {
        List<Reception> lstreception=findBynum_rcp(r1.getNum_rcp());
        if (!lstreception.isEmpty() )
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Reception _Reception = receptionrepos.save(r1);
        return new ResponseEntity<>(_Reception , HttpStatus.CREATED);
    }
    catch (Exception e ) { return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
    }
    private List<Reception> findBynum_rcp (String num_rcp)
    {
        return receptionrepos.findAll().stream()
                .filter(x -> x.getNum_rcp().equalsIgnoreCase(num_rcp))
                .collect(Collectors.toList());
    }
    public ResponseEntity<?> getReceptionById(Long ID_rcp) {
        Optional<Reception> ReceptionData = receptionrepos.findById(ID_rcp);

        if (ReceptionData.isPresent()) {
            return new ResponseEntity(ReceptionData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity deleteReception(Long ID_rcp)
    {
        Optional<Reception> result= receptionrepos.findById(ID_rcp);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        receptionrepos.deleteById(ID_rcp);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
