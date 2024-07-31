package com.example.db.repository;

import com.example.db.dto.NameProjection;
import com.example.db.model.Universal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.List;

public interface UniversalRepository extends CrudRepository<Universal,String> {
    Universal findByName(String name);
    Universal findByDistance(float dist);

    @Query(value = "SELECT u.distance FROM universal u ORDER BY u.distance", nativeQuery = true)
    List<Integer> getDistinct();

    @Query(value = "SELECT f.name FROM universal f", nativeQuery = true)
    List<NameProjection> allNameUniversals();

}
