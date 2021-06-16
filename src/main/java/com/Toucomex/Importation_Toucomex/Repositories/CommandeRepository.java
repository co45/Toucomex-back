package com.Toucomex.Importation_Toucomex.Repositories;

import com.Toucomex.Importation_Toucomex.Models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {



    @Query(value = "SELECT DISTINCT numero FROM Commande",nativeQuery = true)
     List<String> getAllCommandeNum();

    @Query(value = "SELECT * FROM Commande WHERE numero=?1",nativeQuery = true)
    List<Commande> getPdtCmd(String numCmd);

    @Query("SELECT c FROM Commande c WHERE c.numero = ?1")
     List<Commande> findpdts(String ref);

    boolean existsByNumero(String numero);

    //---
    @Query(value = "SELECT COUNT(*) FROM Commande WHERE numero =:numc ;",nativeQuery = true)
    Integer CommandeExists(@Param("numc") String numc);


    @Query(value = "SELECT * FROM Commande WHERE numero =:numc ;",nativeQuery = true)
    Commande getCommandeByNum(@Param("numc") String numc);
    //---


}
