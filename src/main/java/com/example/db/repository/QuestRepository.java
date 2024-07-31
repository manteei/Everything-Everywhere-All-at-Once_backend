package com.example.db.repository;

import com.example.db.model.Quest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface QuestRepository extends CrudRepository<Quest, String> {
    @Query("SELECT a FROM Quest a")
    @JsonManagedReference
    List<Quest> findAllQuests();

    Quest findByName(String name);

    @Transactional
    void deleteByName(String name);

    @Query(value = "SELECT * FROM Quest ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
    List<Quest> findRandomQuests();

}
