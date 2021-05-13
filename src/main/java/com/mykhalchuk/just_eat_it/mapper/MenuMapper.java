package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.MenuDto;
import com.mykhalchuk.just_eat_it.domain.entity.Menu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuDto toDto(Menu menu);
}
