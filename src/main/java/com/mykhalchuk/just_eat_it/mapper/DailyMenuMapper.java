package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.DailyMenuDto;
import com.mykhalchuk.just_eat_it.domain.entity.DailyMenu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DailyDishMapper.class)
public interface DailyMenuMapper {

    DailyMenuDto toDto(DailyMenu dailyMenu);
}
