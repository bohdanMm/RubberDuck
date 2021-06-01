package com.mykhalchuk.just_eat_it.domain.entity;

import com.mykhalchuk.just_eat_it.domain.enums.DailyDishType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DailyDish extends IdHolder {

    private Long id;

    @ManyToOne
    private DailyMenu dailyMenu;

    @ManyToOne
    private Dish dish;

    @Enumerated(EnumType.STRING)
    private DailyDishType type;

    private Integer calories;

    @OneToMany(mappedBy = "dailyDish", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<DailyDishIngredient> dailyDishIngredients;
}
