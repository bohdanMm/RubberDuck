package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.dto.IngredientDto;
import com.mykhalchuk.just_eat_it.domain.entity.DailyDishIngredient;
import com.mykhalchuk.just_eat_it.domain.entity.Ingredient;
import com.mykhalchuk.just_eat_it.domain.enums.AmountType;
import com.mykhalchuk.just_eat_it.exception.EntityNotFoundException;
import com.mykhalchuk.just_eat_it.mapper.IngredientMapper;
import com.mykhalchuk.just_eat_it.repository.DailyDishIngredientRepository;
import com.mykhalchuk.just_eat_it.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;
    private final DailyDishIngredientRepository dailyDishIngredientRepository;

    public void create(IngredientDto ingredientDto) {
        ingredientDto.setAmountType(AmountType.GRAM);
        Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);
        ingredientRepository.save(ingredient);
    }

    public List<IngredientDto> getAll() {
        return ingredientRepository.findAll()
                .stream()
                .sorted((Comparator.comparing(Ingredient::getName)))
                .map(ingredientMapper::toDto)
                .collect(Collectors.toList());
    }

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
    }

    public void deleteDailyIngredientsByDailyDishId(Long dailyDishId){
        List<DailyDishIngredient> dailyDishIngredients = dailyDishIngredientRepository.findByDailyDishId(dailyDishId);
        dailyDishIngredientRepository.deleteAll(dailyDishIngredients);
    }
}
