package com.mykhalchuk.just_eat_it.domain;

import com.mykhalchuk.just_eat_it.domain.entity.IdHolder;
import com.mykhalchuk.just_eat_it.domain.entity.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class DailyMenu extends IdHolder {

    @ManyToOne
    private Menu menu;

    private LocalDate localDate;
}
