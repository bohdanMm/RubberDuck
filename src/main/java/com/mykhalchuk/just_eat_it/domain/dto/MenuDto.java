package com.mykhalchuk.just_eat_it.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MenuDto {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;
}
