package com.Toucomex.Importation_Toucomex.Repositories;

import com.Toucomex.Importation_Toucomex.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
