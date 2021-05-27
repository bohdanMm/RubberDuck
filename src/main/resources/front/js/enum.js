async function getGenders() {
    try {
        const response = await fetch(url + "enum/genders", {
            method: 'GET'
        });
        const json = await response.json();
        return json;
    } catch (error) {
        console.error('Помилка:', error);
    }
}

async function getTrainingRates() {
    try {
        const response = await fetch(url + "enum/training-rates", {
            method: 'GET'
        });
        return await response.json();
    } catch (error) {
        console.error('Помилка:', error);
    }
}

async function getDishCategories() {
    try {
        const response = await fetch(url + "enum/dish-categories", {
            method: 'GET'
        });
        return await response.json();
    } catch (error) {
        console.error('Помилка:', error);
    }
}

function mapDishTime(dishTime){
    switch (dishTime) {
        case "BREAKFAST":
            return "Сніданок";
        case "LUNCH":
            return "Перекус";
        case "DINNER":
            return "Обід";
        case "SNACK":
            return "Перекус";
        case "SUPPER":
            return "Вечеря";
    }
}

function mapIngredientAmount(ingredientAmount){
    switch (ingredientAmount) {
        case "GRAM":
            return "Грам";
        case "COUNT":
            return "Штук";
        case "TEASPOON":
            return "Чайна ложка";
        case "SPOON":
            return "Ложка";
    }
}