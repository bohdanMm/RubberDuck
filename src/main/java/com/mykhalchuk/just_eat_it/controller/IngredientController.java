package com.mykhalchuk.just_eat_it.controller;

import com.mykhalchuk.just_eat_it.domain.dto.IngredientDto;
import com.mykhalchuk.just_eat_it.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping("create")
    public void create(@RequestBody IngredientDto ingredientDto){
        ingredientService.create(ingredientDto);
    }

    @GetMapping("all")
    public List<IngredientDto> getAll(){
        return ingredientService.getAll();
    }
}
