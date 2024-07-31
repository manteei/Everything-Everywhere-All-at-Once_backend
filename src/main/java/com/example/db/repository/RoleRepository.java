package com.example.db.repository;

import com.example.db.model.Role;
import com.example.db.model.User;
import com.example.db.model.UserRole;
import com.example.db.model.enums.RoleName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,String> {
    Role findByName(RoleName name);

    @Query("SELECT r.name FROM UserRole ur INNER JOIN ur.user u INNER JOIN ur.role r WHERE u.username = :login")
    String findByUser(@Param("login") String login);

}
