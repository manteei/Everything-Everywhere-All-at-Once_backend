package com.example.db.repository;

import com.example.db.dto.EmployerProjection;
import com.example.db.model.Employer;
import com.example.db.model.enums.StatusOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployerRepository extends CrudRepository<Employer,String> {

    @Query(value = "SELECT e.order_id, e.hero,e.coordinator FROM employer e INNER JOIN orders o on e.order_id = o.id WHERE o.status = :state", nativeQuery = true)
    List<EmployerProjection> getEmployersByOrderStatus(@Param("state") String state);

}
