package com.example.db.repository;

import com.example.db.dto.AbilityProjection;
import com.example.db.dto.UserModelProjection;
import com.example.db.model.Ability;
import com.example.db.model.HeroAbility;
import com.example.db.model.HeroAbilityId;
import com.example.db.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroAbilityRepository extends CrudRepository<HeroAbility, HeroAbilityId> {

    @Query(value = "SELECT a.title, ha.mastery_percentage FROM hero_ability ha INNER JOIN ability a on ha.ability_id = a.id and ha.login = :person", nativeQuery = true)
    List<AbilityProjection> getAbility (@Param("person") String person);

    @Query("SELECT ha FROM HeroAbility ha WHERE ha.ability = :ability AND ha.user = :user")
    HeroAbility findByAbilityAndUser(@Param("ability") Ability ability, @Param("user") User user);


    @Query(value = "select * from get_user_skill_model(:person)", nativeQuery = true)
    List<UserModelProjection> getUserModel(@Param("person") String person);
}
