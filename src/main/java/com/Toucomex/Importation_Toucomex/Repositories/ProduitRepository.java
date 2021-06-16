package com.Toucomex.Importation_Toucomex.Repositories;

import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProduitRepository extends JpaRepository <Produit, Long> {


    //----------------------

    @Query(value = "SELECT * FROM Produit WHERE reference =:pref  ;",nativeQuery = true)
    Produit getProduitsByRefPdt(@Param("pref") String pref);

    //-----------------
    @Query("SELECT u FROM Produit u WHERE u.reference = ?1")
    public Produit findbynamepdt(String ref);

    @Query("SELECT u FROM Produit u WHERE u.reference = ?1")
    public Produit findbynamepdtp(String ref);



    @Query("SELECT u FROM Produit u WHERE u.reference=?1")
    Produit findProduitsByReference (String ref);

    @Query(value = "SELECT DISTINCT reference FROM Produit",nativeQuery = true)
    List<String> getAllPdtNum();


}
