package com.mykhalchuk.just_eat_it.mapper;

import com.mykhalchuk.just_eat_it.domain.dto.MainUserDto;
import com.mykhalchuk.just_eat_it.domain.entity.MainUser;
import com.mykhalchuk.just_eat_it.domain.enums.Role;
import com.mykhalchuk.just_eat_it.exception.BadRequestException;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring", imports = Role.class)
public interface MainUserMapper {

    @Mapping(target = "role", expression = "java(Role.USER)")
    MainUser toEntity(MainUserDto dto);

    @AfterMapping
    default void afterToEntityMapper(@MappingTarget MainUser mainUser, MainUserDto dto) {
        setBodyMassIndex(mainUser, dto);
        mainUser.setDailyCalories(calculateDailyCalories(dto));
    }

    default void setBodyMassIndex(MainUser mainUser, MainUserDto dto) {
        BigDecimal heightInMeter = BigDecimal.valueOf(dto.getHeight()).movePointLeft(2);
        BigDecimal weight = BigDecimal.valueOf(dto.getWeight());
        BigDecimal bodyMassIndex = weight.divide(heightInMeter, 2, RoundingMode.CEILING)
                .divide(heightInMeter, 2, RoundingMode.CEILING);
        mainUser.setBodyMassIndex(bodyMassIndex);
    }

    default int calculateDailyCalories(MainUserDto dto) {
        int dailyCalories;
        switch (dto.getGender()) {
            case MALE:
                dailyCalories = calculateDailyCaloriesForMan(dto);
                break;
            case FEMALE:
                dailyCalories = calculateDailyCaloriesForWomen(dto);
                break;
            case OTHER:
                dailyCalories = (calculateDailyCaloriesForMan(dto) + calculateDailyCaloriesForWomen(dto));
                break;
            default:
                throw new BadRequestException("You entered incorrect gender");
        }
        return (int) (dailyCalories * dto.getTrainingRate().getInfluence());

    }

    default int calculateDailyCaloriesForMan(MainUserDto dto) {
        double weightInfluence = 13.4 * dto.getWeight();
        double heightInfluence = 4.8 * dto.getHeight();
        double ageInfluence = 5.7 * dto.getAge();
        return (int) (88.38 + weightInfluence + heightInfluence - ageInfluence);
    }

    default int calculateDailyCaloriesForWomen(MainUserDto dto) {
        double weightInfluence = 9.2 * dto.getWeight();
        double heightInfluence = 3.1 * dto.getHeight();
        double ageInfluence = 4.3 * dto.getAge();
        return (int) (447.6 + weightInfluence + heightInfluence - ageInfluence);
    }
}
