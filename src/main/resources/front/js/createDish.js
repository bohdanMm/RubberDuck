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


async function createDish() {
    let arr = [];
    let ingredients = document.getElementsByClassName("ingredient");
    let ingredientsAmounts = document.getElementsByClassName("ingredientAmount");
    for (let i = 0; i < ingredients.length; i++) {
        let ingredient = {
            ingredientId: ingredients[i].value,
            amount: ingredientsAmounts[i].value
        };
        arr.push(ingredient);
        console.log(arr);
    }
    let dish = {
        name: document.querySelector('#name').value,
        dishIngredients: arr
    };

    try {
        await fetch(url + "dish/create", {
            method: 'POST',
            body: JSON.stringify(dish),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    } catch (error) {
        console.error('Помилка:', error);
    }
}