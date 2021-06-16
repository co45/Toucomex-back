package com.Toucomex.Importation_Toucomex.Controllers;


import com.Toucomex.Importation_Toucomex.Auth.model.User;
import com.Toucomex.Importation_Toucomex.Auth.repository.UserRepository;
import com.Toucomex.Importation_Toucomex.Models.Commande;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Repositories.CommandeRepository;
import com.Toucomex.Importation_Toucomex.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    private CommandeRepository cr;

    @Autowired
    private CommandeService cs;

    public CommandeController(CommandeService cs) {
        this.cs = cs;
    }

    @GetMapping("/{id}")
    public Optional<Commande> getCommandeById(@PathVariable("id") long id) {
        return cr.findById(id);
    }

    @GetMapping("/cmds")
    public ResponseEntity<List<Commande>> getAllCmds() {
        System.out.println("TEST ");
        System.out.println("LIST : "+ cs.getallCommande().toString());

        return cs.getallCommande(); }

    @GetMapping("/pdtCmds/{cmd}")
    public List<Commande> getAllPdtCmd(@PathVariable("cmd") String cmd) { return cr.getPdtCmd(cmd); }


    @GetMapping("/cmdnums")
    public List<String> getAllCmdsNum() { return cr.getAllCommandeNum(); }

    @PostMapping("/add")
    public ResponseEntity<Commande> createCmd(@Valid @RequestBody Commande cmd) {
        return cs.addCommande(cmd);
    }

}
