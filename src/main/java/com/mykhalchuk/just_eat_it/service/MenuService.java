package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.dto.DailyMenuDto;
import com.mykhalchuk.just_eat_it.domain.dto.MenuDto;
import com.mykhalchuk.just_eat_it.domain.entity.*;
import com.mykhalchuk.just_eat_it.domain.enums.DailyDishType;
import com.mykhalchuk.just_eat_it.mapper.DailyMenuMapper;
import com.mykhalchuk.just_eat_it.mapper.MenuMapper;
import com.mykhalchuk.just_eat_it.repository.DailyMenuRepository;
import com.mykhalchuk.just_eat_it.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final Integer MENU_FOR_DAYS = 7;

    private final DishService dishService;
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final DailyMenuRepository dailyMenuRepository;
    private final DailyMenuMapper dailyMenuMapper;

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
            dailyDishes.add(buildDailyDish(DailyDishType.BREAKFAST, breakfasts.get(i % breakfasts.size()), dailyMenu));
            dailyDishes.add(buildDailyDish(DailyDishType.DINNER, dinners.get(i % dinners.size()), dailyMenu));
            dailyDishes.add(buildDailyDish(DailyDishType.SNACK, snacks.get(i % snacks.size()), dailyMenu));
            dailyDishes.add(buildDailyDish(DailyDishType.SUPPER, suppers.get(i % suppers.size()), dailyMenu));

            menu.getDailyMenus().add(dailyMenu);
        }

        menuRepository.save(menu);
    }

    public MenuDto getByUserId(Long userId) {
        return menuMapper.toDto(menuRepository.getByUserId(userId));
    }

    public DailyMenuDto getDailyMenu(Long userId, LocalDate menuDate) {
        return dailyMenuMapper.toDto(dailyMenuRepository.findByUserIdAndDate(userId, menuDate));
    }

    private DailyDish buildDailyDish(DailyDishType dailyDishType, Dish dish, DailyMenu dailyMenu) {
        return DailyDish.builder()
                .type(dailyDishType)
                .dish(dish)
                .calories(dish.getCalories())
                .dailyMenu(dailyMenu)
                .build();

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
                .endDate(dailyMenuDate.plusDays(MENU_FOR_DAYS))
                .user(user)
                .dailyMenus(new ArrayList<>())
                .build();
    }

}
