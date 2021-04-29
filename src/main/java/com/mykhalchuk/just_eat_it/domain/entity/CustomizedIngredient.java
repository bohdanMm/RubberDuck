package com.mykhalchuk.just_eat_it.domain.entity;

import com.mykhalchuk.just_eat_it.domain.enums.CustomizationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class CustomizedIngredient extends IdHolder {

    @ManyToOne
    private MainUser user;

    @ManyToOne
    private Ingredient ingredient;

    @Enumerated(value = EnumType.STRING)
    private CustomizationType type;
}
