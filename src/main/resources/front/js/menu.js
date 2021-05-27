let menuDate = new Date();

window.onload = function () {
    addNavigationMenu();
    getDish(menuDate);
}

async function getDish(menuDate) {
    try {
        let user = JSON.parse(localStorage.getItem("user"));
        const response = await fetch(url + "menu/daily/user/" + user.id + "?menuDate=" + parsDate(menuDate), {
            method: 'GET'
        })
            .then(response => response.json())
            .then(json => {
                let dishes = json.dailyDishes;
                for (let i = 0; i < dishes.length; i++) {
                    addDish(dishes[i])
                }
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

function addDish(dish) {
    let list = document.querySelector("#dishes");
    let li = document.createElement("li");
    let div = document.createElement("div");
    let timeToEat = document.createElement("h2");
    let name = document.createElement("h2");
    let calories = document.createElement("h3");
    let image = document.createElement("img");
    li.value = dish.dishId;
    li.addEventListener("click", function () {redirectToDish(event, this.value)}, false)
    div.classList.add("dish");
    timeToEat.innerHTML = mapDishTime(dish.type);
    name.innerHTML = dish.dishName;
    calories.innerHTML = "Калорійність: " + dish.calories;
    div.append(timeToEat);
    div.append(name);
    div.append(calories);
    div.append(image);
    image.classList.add("dishImg");
    image.src = dish.dishPictureUrl;
    li.append(div)
    list.append(li);
}

function parsDate(date) {
    let dd = String(date.getDate()).padStart(2, '0');
    let mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = date.getFullYear();
    return yyyy + '-' + mm + '-' + dd;
}

function nextDate() {
    let dishes = document.getElementById("dishes");
    menuDate.setDate(menuDate.getDate() + 1);
    clearList(dishes);
    getDish(menuDate);

}

function previousDate() {
    let dishes = document.getElementById("dishes");
    menuDate.setDate(menuDate.getDate() - 1);
    clearList(dishes);
    getDish(menuDate);
}

function clearList(list) {
    while (list.firstChild) {
        list.removeChild(list.firstChild);
    }
}

function redirectToDish(event, dishId){
    window.location.href = 'dish.html?id=' + dishId;
}
