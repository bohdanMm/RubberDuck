package com.mykhalchuk.just_eat_it.repository;

import com.mykhalchuk.just_eat_it.domain.entity.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Long> {

    @Query("select dm from DailyMenu dm " +
            "join dm.menu m " +
            "where m.user.id = :userId and dm.menuDate = :menuDate")
    DailyMenu findByUserIdAndDate(Long userId, LocalDate menuDate);
}
