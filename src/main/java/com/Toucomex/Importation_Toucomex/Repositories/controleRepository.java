package com.Toucomex.Importation_Toucomex.Repositories;

import com.Toucomex.Importation_Toucomex.Models.ControleTech;
import com.Toucomex.Importation_Toucomex.Models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface controleRepository extends JpaRepository<ControleTech,Long> {
    @Query(value = "SELECT from produit where ref_pdt=?1",nativeQuery = true)
    List<Produit> getPdtsFromByRefs (String ref);


}
