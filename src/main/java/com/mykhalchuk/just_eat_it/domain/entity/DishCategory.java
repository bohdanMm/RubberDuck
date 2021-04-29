package com.mykhalchuk.just_eat_it.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class DishCategory extends IdHolder{

    private String name;

    private String displayName;
}
