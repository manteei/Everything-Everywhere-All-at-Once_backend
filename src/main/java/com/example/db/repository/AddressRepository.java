package com.example.db.repository;

import com.example.db.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,String> {
}
