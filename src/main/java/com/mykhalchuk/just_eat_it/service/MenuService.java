package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.dto.DailyMenuDto;
import com.mykhalchuk.just_eat_it.domain.dto.MenuDto;
import com.mykhalchuk.just_eat_it.domain.dto.dish.DishDto;
import com.mykhalchuk.just_eat_it.domain.entity.*;
import com.mykhalchuk.just_eat_it.domain.enums.DailyDishType;
import com.mykhalchuk.just_eat_it.mapper.DailyMenuMapper;
import com.mykhalchuk.just_eat_it.mapper.MenuMapper;
import com.mykhalchuk.just_eat_it.repository.DailyMenuRepository;
import com.mykhalchuk.just_eat_it.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final Integer MENU_FOR_DAYS = 7;

    private final DishService dishService;
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final DailyMenuRepository dailyMenuRepository;
    private final DailyMenuMapper dailyMenuMapper;
    private final IngredientService ingredientService;

    public void createMenu(MainUser user) {
        List<Dish> breakfasts = dishService.getShuffledByCategory(DailyDishType.BREAKFAST.name());
        List<Dish> dinners = dishService.getShuffledByCategory(DailyDishType.DINNER.name());
        List<Dish> snacks = dishService.getShuffledByCategory(DailyDishType.SNACK.name());
        List<Dish> suppers = dishService.getShuffledByCategory(DailyDishType.SUPPER.name());

        LocalDate dailyMenuDate = LocalDate.now();
        Menu menu = buildMenu(user, dailyMenuDate);

        for (int i = 0; i < MENU_FOR_DAYS; i++) {
            DailyMenu dailyMenu = buildDailyMenu(dailyMenuDate, menu);
            List<DailyDish> dailyDishes = dailyMenu.getDailyDishes();

            dailyDishes.add(buildDailyDish(DailyDishType.BREAKFAST, breakfasts.get(i % breakfasts.size()), dailyMenu, user.getDailyCalories()));
            dailyDishes.add(buildDailyDish(DailyDishType.DINNER, dinners.get(i % dinners.size()), dailyMenu, user.getDailyCalories()));
            dailyDishes.add(buildDailyDish(DailyDishType.SNACK, snacks.get(i % snacks.size()), dailyMenu, user.getDailyCalories()));
            dailyDishes.add(buildDailyDish(DailyDishType.SUPPER, suppers.get(i % suppers.size()), dailyMenu, user.getDailyCalories()));

            menu.getDailyMenus().add(dailyMenu);
            dailyMenuDate = dailyMenuDate.plusDays(1);
        }

        menuRepository.save(menu);
    }

    public MenuDto getByUserId(Long userId) {
        return menuMapper.toDto(menuRepository.getByUserId(userId));
    }

    public DailyMenuDto getDailyMenu(Long userId, LocalDate menuDate) {
        DailyMenu dailyMenu = dailyMenuRepository.findByUserIdAndDate(userId, menuDate);
        dailyMenu.getDailyDishes()
                .sort(Comparator.comparing(dish -> dish.getType().getOrder()));
        return dailyMenuMapper.toDto(dailyMenu);
    }

    @Transactional
    public void substituteDish(Long dailyDishId, Long dishId){
        DailyDish dailyDish = dishService.getDailyDishById(dailyDishId);
        Dish newDish = dishService.getById(dishId);
        DailyMenu dailyMenu = dailyDish.getDailyMenu();
        Integer userCalories = dailyMenu.getMenu().getUser().getDailyCalories();
        DailyDish newDailyDish = buildDailyDish(dailyDish.getType(), newDish, dailyMenu, userCalories);
        ingredientService.deleteDailyIngredientsByDailyDishId(dailyDishId);
        dailyMenu.getDailyDishes().remove(dailyDish);
        dailyMenu.getDailyDishes().add(newDailyDish);
        ingredientService.deleteDailyIngredientsByDailyDishId(dailyDishId);
        dailyMenuRepository.save(dailyMenu);
    }

    private DailyDish buildDailyDish(DailyDishType dailyDishType, Dish dish, DailyMenu dailyMenu,
                                     Integer userDailyCalories) {
        BigDecimal dishCoefficient = BigDecimal.valueOf(userDailyCalories)
                .multiply(dailyDishType.getPercent())
                .divide(BigDecimal.valueOf(dish.getCalories()), 2, RoundingMode.DOWN);
        DailyDish dailyDish = DailyDish.builder()
                .type(dailyDishType)
                .dish(dish)
                .calories(dish.getCalories())
                .dailyMenu(dailyMenu)
                .build();
        List<DailyDishIngredient> dailyDishIngredients = dish.getDishIngredients()
                .stream()
                .map(ingredient -> buildDailyDishIngredient(ingredient, dailyDish, dishCoefficient))
                .collect(Collectors.toList());
        int dailyDishCalories = dailyDishIngredients
                .stream()
                .mapToInt(DailyDishIngredient::getCalories)
                .sum();
        dailyDish.setDailyDishIngredients(dailyDishIngredients);
        dailyDish.setCalories(dailyDishCalories);
        return dailyDish;
    }

    private DailyDishIngredient buildDailyDishIngredient(DishIngredient dishIngredient, DailyDish dailyDish,
                                                         BigDecimal coefficient) {
        DailyDishIngredient dailyDishIngredient = DailyDishIngredient.builder()
                .amountType(dishIngredient.getAmountType())
                .amount(BigDecimal.valueOf(dishIngredient.getAmount()).multiply(coefficient).intValue())
                .dailyDish(dailyDish)
                .ingredient(dishIngredient.getIngredient())
                .build();
        int dishIngredientCalories = BigDecimal.valueOf(dailyDishIngredient.getAmount())
                .multiply(BigDecimal.valueOf(dailyDishIngredient.getIngredient().getCalories()))
                .divide(new BigDecimal("100"), 2, RoundingMode.DOWN)
                .intValue();
        dailyDishIngredient.setCalories(dishIngredientCalories);
        return dailyDishIngredient;
    }

    private DailyMenu buildDailyMenu(LocalDate dailyMenuDate, Menu menu) {
        return DailyMenu.builder()
                .menu(menu)
                .menuDate(dailyMenuDate)
                .dailyDishes(new ArrayList<>())
                .build();
    }

    private Menu buildMenu(MainUser user, LocalDate dailyMenuDate) {
        return Menu.builder()
                .startDate(dailyMenuDate)
                .endDate(dailyMenuDate.plusDays(MENU_FOR_DAYS).minusDays(1))
                .user(user)
                .dailyMenus(new ArrayList<>())
                .build();
    }

}
