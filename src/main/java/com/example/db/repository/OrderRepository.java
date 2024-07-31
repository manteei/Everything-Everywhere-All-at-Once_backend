package com.example.db.repository;

import com.example.db.dto.AddressProjection;
import com.example.db.dto.MonsterProjection;
import com.example.db.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,String> {

    @Query(value = "SELECT * FROM get_order()", nativeQuery = true)
    List<MonsterProjection> getOrders();

    Order findById(Integer id);

    @Query(value = "SELECT * FROM get_info_about_order(:id)", nativeQuery = true)
    List<AddressProjection> getAddress(@Param("id")Integer id);
}
