window.onload = function () {
    addNavigationMenu();
    let urlParams = new URLSearchParams(window.location.search);
    getDishById(urlParams.get("id"));
}

async function getDishById(dishId) {
    try {
        await fetch(url + "dish/daily-dish/" + dishId, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(json => {
                console.log(json)
                setDishData(json);
                let list = document.getElementById("ingredients");
                for (let i = 0; i < json.dishIngredients.length; i++) {
                    list.appendChild(createIngredient(json.dishIngredients[i]));
                }
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

function setDishData(json){
    let name = document.querySelector("#name");
    let dishImage = document.querySelector("#dishImg");
    let calories = document.querySelector("#calories");
    name.innerHTML = json.name;
    dishImage.src = json.pictureUrl;
    calories.innerHTML = calories.innerHTML +  json.calories;
}

function createIngredient(ingredientJson){
    let li = document.createElement('li');
    let div = document.createElement('div');
    div.classList.add("ingredient");
    let name = document.createElement('span');
    let amount = document.createElement('span');
    name.innerHTML = ingredientJson.ingredientName;
    amount.innerHTML = ingredientJson.amount + " " + mapIngredientAmount(ingredientJson.amountType);
    div.append(name, amount);
    li.append(div);
    return li;
}