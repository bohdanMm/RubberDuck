package com.mykhalchuk.just_eat_it.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Dish extends IdHolder {

    private String name;

    private String pictureUrl;

    @OneToMany(mappedBy = "dish", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<DishIngredient> dishIngredients;

    @ManyToMany
    private List<DishCategory> dishCategories;
}
