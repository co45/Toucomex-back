package com.Toucomex.Importation_Toucomex.Repositories;

import com.Toucomex.Importation_Toucomex.Models.Commande;
import com.Toucomex.Importation_Toucomex.Models.Fournisseur;
import com.Toucomex.Importation_Toucomex.Models.ProduitCommande;
import com.Toucomex.Importation_Toucomex.Models.ProduitCommandeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface listProduitCommandeRepository extends JpaRepository<ProduitCommande, ProduitCommandeId> {

//    @Query(value = "UPDATE TABLE public.produit_commande pc SET pc.id_f=?1 WHERE pc.produit_id=?2")
//    update ProduitCommande p set u.id_f= = false where u.lastLoginDate < :date"
//    public void updateCmd(Long idf,Long idp);

//    @Query(value = "INSERT INTO produit_commande (id_f) VALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway');",nativeQuery = true)
//    public void insertCmd(@Param("idf") Long idf,@Param("idp")Long idp);

    @Query(value = "SELECT * FROM ProduitCommande WHERE produit_id =:pdid ;",nativeQuery = true)
    ProduitCommande getCommandeProduitByPid(@Param("pdid") String pdid);


}
