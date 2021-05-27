let counter;

window.onload = function () {
    addNavigationMenu();
    counter = 0;
}


function addIngredient() {
    let buttonLi = document.querySelector("#addIngredientButtonLi");
    let li = document.createElement("li");
    createFieldForIngredient(li);
    createFieldForIngredientAmount(li);
    buttonLi.before(li);
    counter++;
}

function createFieldForIngredient(li) {
    let label = document.createElement("Label");
    let select = document.createElement("select");
    select.id = "select" + counter;
    select.className = "ingredient";
    label.setAttribute("for", select.id);
    label.innerHTML = "Назва інгредієнту";
    li.append(label);
    li.append(select);
    getIngredients(select.id);
}

function createFieldForIngredientAmount(li) {
    let label = document.createElement("Label");
    let input = document.createElement("input");
    input.id = "input" + counter;
    input.className = "ingredientAmount";
    label.setAttribute("for", input.id);
    label.innerHTML = "Кількість інгрідієнту";
    li.append(label);
    li.append(input);
}

function addCategory() {
    let buttonLi = document.querySelector("#addCategoryButtonLi");
    let li = document.createElement("li");
    createFieldForCategory(li);
    buttonLi.before(li);
    counter++;
}

function createFieldForCategory(li) {
    let label = document.createElement("Label");
    let select = document.createElement("select");
    select.id = "select" + counter;
    select.className = "category";
    label.setAttribute("for", select.id);
    label.innerHTML = "Категорія";
    li.append(label);
    li.append(select);
    setDishCategories(select);
}

async function setDishCategories(select) {
    let dishCategories = await getDishCategories();

    for (let i = 0; i < dishCategories.length; i++) {
        let opt = document.createElement('option');
        opt.value = dishCategories[i];
        opt.innerHTML = mapDishTime(opt.value);
        select.appendChild(opt);
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


async function createDish() {
    let ingredientArr = [];
    let ingredients = document.getElementsByClassName("ingredient");
    let ingredientsAmounts = document.getElementsByClassName("ingredientAmount");
    for (let i = 0; i < ingredients.length; i++) {
        let ingredient = {
            ingredientId: ingredients[i].value,
            amount: ingredientsAmounts[i].value
        };
        ingredientArr.push(ingredient);
    }

    let categoryArr = [];
    let categories = document.getElementsByClassName("category");
    for (let i = 0; i < categories.length; i++) {
        categoryArr.push(categories[i].value);
    }

    let dish = {
        name: document.querySelector('#name').value,
        dishIngredients: ingredientArr,
        dishCategories: categoryArr
    };

    console.log(dish)
    try {
        await fetch(url + "dish/create", {
            method: 'POST',
            body: JSON.stringify(dish),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => location.reload());;
    } catch (error) {
        console.error('Помилка:', error);
    }
}