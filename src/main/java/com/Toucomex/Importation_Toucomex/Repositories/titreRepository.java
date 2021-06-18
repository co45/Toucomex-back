package com.Toucomex.Importation_Toucomex.Repositories;

import com.Toucomex.Importation_Toucomex.Models.Facture;
import com.Toucomex.Importation_Toucomex.Models.Titre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface titreRepository extends JpaRepository<Titre,Long> {

    @Query(value = "SELECT t FROM Titre t Where t.Num_t= ?1")
    public Titre getBytitrenum (String num);
}
