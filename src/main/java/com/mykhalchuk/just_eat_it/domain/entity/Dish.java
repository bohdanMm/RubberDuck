package com.mykhalchuk.just_eat_it.domain.entity;

import com.mykhalchuk.just_eat_it.domain.enums.DailyDishType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Dish extends IdHolder {

    private String name;

    private String pictureUrl;

    private Integer calories;

    @OneToMany(mappedBy = "dish", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<DishIngredient> dishIngredients;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<DailyDishType> dishCategories;
}
