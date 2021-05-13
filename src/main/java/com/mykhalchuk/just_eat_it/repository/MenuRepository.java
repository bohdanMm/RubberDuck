package com.mykhalchuk.just_eat_it.repository;

import com.mykhalchuk.just_eat_it.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu getByUserId(Long userId);
}
