package com.Toucomex.Importation_Toucomex.Repositories;

import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {

    @Query(value = "SELECT f FROM Fournisseur f WHERE f.nom =:nom")
    public Fournisseur findbynamefsr(@Param("nom") String nom);

    @Query(value = "SELECT * FROM Fournisseur WHERE nom =:nomf ;",nativeQuery = true)
    Fournisseur getFournisseurBynom(@Param("nomf") String nomf);

    @Query(value = "SELECT id_fsr,adresse_fsr,code_fsr,libelle_fsr,nom,swift FROM Fournisseur;",nativeQuery = true)
    List<Fournisseur> getAllFscs();




}
