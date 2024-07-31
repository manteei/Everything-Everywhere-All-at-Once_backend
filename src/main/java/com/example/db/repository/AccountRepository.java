package com.example.db.repository;

import com.example.db.model.Account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountRepository extends CrudRepository<Account,String> {
    @Query("SELECT a FROM Account a  WHERE a.login = :login")
    Account findByLogin(String login);


}
