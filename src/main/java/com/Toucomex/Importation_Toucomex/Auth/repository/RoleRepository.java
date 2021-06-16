package com.Toucomex.Importation_Toucomex.Auth.repository;

import com.Toucomex.Importation_Toucomex.Auth.model.Role;
import com.Toucomex.Importation_Toucomex.Auth.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);

    /*@Query(value = "INSERT into Role (name) SELECT '1448523' Where not exists(select * from tablename where code='1448523')",nativeQuery = true)
    public void roleadd();*/

}