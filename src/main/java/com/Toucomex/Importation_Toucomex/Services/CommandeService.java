package com.Toucomex.Importation_Toucomex.Services;

import com.Toucomex.Importation_Toucomex.Models.Commande;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commanderepos;

    public CommandeService(CommandeRepository commanderepos) {
        this.commanderepos = commanderepos;
    }

    public List <String> getAllCommande() {
        return commanderepos.getAllCommandeNum();
    }



    public ResponseEntity<Commande> addCommande(Commande c1) {
        try {
            List<Commande> lstcommande = findByNum_cmd(c1.getNumero());
            if (!lstcommande.isEmpty())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            Commande _Commande = commanderepos.save(c1);
            return new ResponseEntity<>(_Commande, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Commande> findByNum_cmd(String Num_cmd) {
        return commanderepos.findAll().stream()
                .filter(x -> x.getNumero().equalsIgnoreCase(Num_cmd))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getCommandeById(Long ID_cmd) {
        Optional<Commande> CommandeData = commanderepos.findById(ID_cmd);

        if (CommandeData.isPresent()) {
            return new ResponseEntity(CommandeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Commande>> getallCommande()
    {
        List<Commande> lstcmds=commanderepos.findAll();
        if(lstcmds.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(lstcmds,HttpStatus.OK);
    }
}