package com.Toucomex.Importation_Toucomex.Repositories;

import com.Toucomex.Importation_Toucomex.Models.Reception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReceptionRepository extends JpaRepository<Reception,Long> {

}
