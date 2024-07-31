package com.example.db.repository;

import com.example.db.dto.NameProjection;
import com.example.db.dto.NameRequest;
import com.example.db.model.Monster;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface MonsterRepository extends CrudRepository<Monster,String> {
    Monster findByName(String name);

    @Query(value = "SELECT f.name FROM monsters f ", nativeQuery = true)
    List<NameProjection> allNameMonsters();
}
