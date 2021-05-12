package com.mykhalchuk.just_eat_it.domain.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Menu extends IdHolder {

    @OneToOne
    private MainUser user;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "menu", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<DailyMenu> dailyMenus;
}
