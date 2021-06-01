package com.mykhalchuk.just_eat_it.repository;

import com.mykhalchuk.just_eat_it.domain.entity.MainUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainUserRepository extends JpaRepository<MainUser, Long> {

    Optional<MainUser> findByEmailAndPassword(String email, String password);

    @Query("select case when count(mu) > 0 then true else false end " +
            "from MainUser mu where lower(mu.email) = :email")
    boolean existsByEmail(String email);

    @Query("select u.dailyCalories from MainUser u where u.id = :userId")
    Integer findCaloriesByUserId(Long userId);
}
