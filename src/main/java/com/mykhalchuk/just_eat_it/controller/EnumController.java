package com.mykhalchuk.just_eat_it.controller;

import com.mykhalchuk.just_eat_it.service.EnumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/enum")
public class EnumController {

    private final EnumService enumService;

    @GetMapping("genders")
    public List<String> getGenders(){
        return enumService.getGenders();
    }

    @GetMapping("training-rates")
    public List<String> getTrainingRates(){
        return enumService.getTrainingRates();
    }

    @GetMapping("dish-categories")
    public List<String> getDishCategories(){
        return enumService.getDishTypes();
    }
}
