package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.enums.Gender;
import com.mykhalchuk.just_eat_it.domain.enums.TrainingRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EnumService {

    public List<String> getGenders(){
        return Stream.of(Gender.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public List<String> getTrainingRates(){
        return Stream.of(TrainingRate.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
