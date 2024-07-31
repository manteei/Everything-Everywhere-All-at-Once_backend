package com.example.db.repository;

import com.example.db.model.AuthorizationData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorizationRepository extends CrudRepository<AuthorizationData,String> {
    AuthorizationData findByUsername(String username);
}


