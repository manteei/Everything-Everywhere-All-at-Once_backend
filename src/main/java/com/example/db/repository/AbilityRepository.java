package com.example.db.repository;

import com.example.db.model.Ability;
import org.springframework.data.repository.CrudRepository;

public interface AbilityRepository extends CrudRepository<Ability,String> {
    Ability findByTitle(String text);
}
