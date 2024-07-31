package com.example.db.repository;

import com.example.db.model.Role;
import com.example.db.model.User;
import com.example.db.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole,String> {
    UserRole findByRole(Role role);


}
