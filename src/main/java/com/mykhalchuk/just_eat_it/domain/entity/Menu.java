package com.mykhalchuk.just_eat_it.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Menu extends IdHolder{

    @OneToOne
    private MainUser user;

    private LocalDate startDate;

    private LocalDate endDate;
}
