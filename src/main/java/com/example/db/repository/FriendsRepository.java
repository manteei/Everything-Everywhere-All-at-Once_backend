package com.example.db.repository;

import com.example.db.dto.FriendsProjection;
import com.example.db.model.Friends;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface FriendsRepository extends CrudRepository<Friends,String> {
    @Query(value = "SELECT * FROM get_friends(:person,:status)", nativeQuery = true)
    List<FriendsProjection> getFriends(@Param("person") String person, @Param("status") String status);

    @Query(value = "SELECT f. login1 as friend FROM friends f WHERE f.status = :status and f.login2 = :person", nativeQuery = true)
    List<FriendsProjection> getFriendsRequest(@Param("person") String person,@Param("status") String status);

    @Query(value = "SELECT * FROM get_people(:person)", nativeQuery = true)
    List<FriendsProjection> getPerson(@Param("person") String person);

    @Modifying
    @Query(value = "DELETE FROM friends f where f.login1 = :user1 and f.login2 = :user2 or f.login2 = :user1 and f.login1 = :user2 ", nativeQuery = true)
    @Transactional
    void deleteFriendsByName(@Param("user1") String user1, @Param("user2") String user2);

    @Query("SELECT f FROM Friends f WHERE (f.user1.login = :login1 and f.user2.login = :login2) or (f.user2.login = :login1 and f.user1.login = :login2)")
    Friends findByUser1AndUser2(@Param("login1") String login1, @Param("login2") String login2);
}
