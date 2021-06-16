package com.Toucomex.Importation_Toucomex.Auth.repository;

import com.Toucomex.Importation_Toucomex.Auth.model.Role;
import com.Toucomex.Importation_Toucomex.Auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
   @Query(
           value = "SELECT roles.name FROM Roles INNER JOIN user_roles ON Roles.id = user_roles.role_id INNER JOIN Users ON user_roles.user_id = Users.id WHERE (Username = %?1)",
           nativeQuery = true)
   List<String> getUserRoleByUsername(String role);


}