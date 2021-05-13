package com.mykhalchuk.just_eat_it.domain.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DailyMenu extends IdHolder {

    @ManyToOne
    private Menu menu;

    private LocalDate menuDate;

    @OneToMany(mappedBy = "dailyMenu", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<DailyDish> dailyDishes;
}
