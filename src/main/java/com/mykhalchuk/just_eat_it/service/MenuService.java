package com.mykhalchuk.just_eat_it.service;

import com.mykhalchuk.just_eat_it.domain.entity.*;
import com.mykhalchuk.just_eat_it.domain.enums.DailyDishType;
import com.mykhalchuk.just_eat_it.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private Integer MENU_FOR_DAYS = 1;

    private final DishService dishService;
    private final MenuRepository menuRepository;

    public void createMenu(MainUser user) {
        List<Dish> breakfasts = dishService.getShuffledByCategory(DailyDishType.BREAKFAST.name());
        List<Dish> lunches = dishService.getShuffledByCategory(DailyDishType.LUNCH.name());
        List<Dish> dinners = dishService.getShuffledByCategory(DailyDishType.DINNER.name());
        List<Dish> noonings = dishService.getShuffledByCategory(DailyDishType.NOONING.name());
        List<Dish> suppers = dishService.getShuffledByCategory(DailyDishType.SUPPER.name());

        LocalDate dailyMenuDate = LocalDate.now();
        Menu menu = buildMenu(user, dailyMenuDate);

        for (int i = 0; i < MENU_FOR_DAYS; i++) {
            DailyMenu dailyMenu = buildDailyMenu(dailyMenuDate, menu);
            List<DailyDish> dailyDishes = dailyMenu.getDailyDishes();
            dailyDishes.add(buildDailyDish(DailyDishType.BREAKFAST, breakfasts.get(i % breakfasts.size()), dailyMenu));
            dailyDishes.add(buildDailyDish(DailyDishType.LUNCH, lunches.get(i % lunches.size()), dailyMenu));
            dailyDishes.add(buildDailyDish(DailyDishType.DINNER, dinners.get(i % dinners.size()), dailyMenu));
            dailyDishes.add(buildDailyDish(DailyDishType.NOONING, noonings.get(i % noonings.size()), dailyMenu));
            dailyDishes.add(buildDailyDish(DailyDishType.SUPPER, suppers.get(i % suppers.size()), dailyMenu));

            menu.getDailyMenus().add(dailyMenu);
        }

        menuRepository.save(menu);
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
                .localDate(dailyMenuDate)
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
