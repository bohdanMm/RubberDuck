let menuSelectedDate = new Date();
let dishForChangeId;

window.onload = function () {
    addNavigationMenu();
    setSelectedDate();
    getMenu();
    getDish(menuSelectedDate);
}

function setSelectedDate() {
    document.querySelector("#selectedDate").value = parsDate(menuSelectedDate);
}

async function getDish(menuDate) {
    try {
        let user = JSON.parse(localStorage.getItem("user"));
        await fetch(url + "menu/daily/user/" + user.id + "?menuDate=" + parsDate(menuDate), {
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

async function getMenu() {
    try {
        let user = JSON.parse(localStorage.getItem("user"));
        await fetch(url + "menu/user/" + user.id, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(json => {
                console.log(json)
                document.querySelector("#startDate").value = json.startDate;
                document.querySelector("#endDate").value = json.endDate;
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

function addDish(dish) {
    let list = document.querySelector("#dishes");
    let li = document.createElement("li");
    let div = document.createElement("div");
    let nameContainer = document.createElement("div");
    let timeToEat = document.createElement("h2");
    let name = document.createElement("h2");
    let calories = document.createElement("h3");
    let image = document.createElement("img");
    let changeDishButton = document.createElement("button");
    changeDishButton.innerHTML = "<b>↺</b>";
    changeDishButton.classList.add("changeDishButton");
    changeDishButton.id = dish.dishId;
    changeDishButton.addEventListener("click", function () {
        showModal(dish.id)
    }, false)
    name.id = dish.dishId;
    name.addEventListener("click", function () {
        redirectToDish(event, dish.id)
    }, false)
    div.classList.add("dish");
    timeToEat.innerHTML = mapDishTime(dish.type);
    name.innerHTML = dish.dishName;
    calories.innerHTML = "Калорійність: " + dish.calories;
    nameContainer.append(name);
    nameContainer.append(changeDishButton);
    nameContainer.style.display = "flex";
    nameContainer.style.alignItems = "center";
    div.append(timeToEat);
    div.append(nameContainer);
    div.append(calories);
    div.append(image);
    image.classList.add("dishImg");
    image.src = dish.dishPictureUrl;
    li.append(div)
    list.append(li);
}

function parsDate(date) {
    let dd = String(date.getDate()).padStart(2, '0');
    let mm = String(date.getMonth() + 1).padStart(2, '0');
    let yyyy = date.getFullYear();
    return yyyy + '-' + mm + '-' + dd;
}

function nextDate() {
    let dishes = document.getElementById("dishes");
    menuSelectedDate.setDate(menuSelectedDate.getDate() + 1);
    clearList(dishes);
    getDish(menuSelectedDate);
    setSelectedDate();

}

function previousDate() {
    let dishes = document.getElementById("dishes");
    menuSelectedDate.setDate(menuSelectedDate.getDate() - 1);
    clearList(dishes);
    getDish(menuSelectedDate);
    setSelectedDate();
}

function clearList(list) {
    while (list.firstChild) {
        list.removeChild(list.firstChild);
    }
}

function redirectToDish(event, dishId) {
    window.location.href = 'dish.html?id=' + dishId;
}

function showModal(id) {
    dishForChangeId = id;
    let modal = document.querySelector("#changeDishModal");
    modal.style.display = "block";
    getSubstitute(id);
}

window.onclick = function (event) {
    let modal = document.querySelector("#changeDishModal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function closeModal() {
    let modal = document.querySelector("#changeDishModal");
    modal.style.display = "none";
}

async function getSubstitute(dishId) {
    try {
        dishForChangeId = dishId;
        await fetch(url + "dish/substitute/" + dishId, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(json => {
                let containers = document.getElementsByClassName("dishForChange");
                for (let i = 0; i < json.length; i++){
                    containers[i].innerHTML = '';
                    let name = document.createElement("h2");
                    let image = document.createElement("img");
                    let changeButton = document.createElement("button");
                    let buttonContainer = document.createElement("div");

                    changeButton.innerHTML = "Замінити"
                    changeButton.id = json[i].id;
                    changeButton.addEventListener("click", function () {
                        substituteDish(this.id)
                    }, false);
                    name.innerHTML = "Назва: " + json[i].name;
                    image.classList.add("dishForChangeImg");
                    image.src = json[i].pictureUrl;
                    console.log(containers)
                    containers[i].append(name);
                    containers[i].append(image);
                    buttonContainer.append(changeButton)
                    containers[i].append(buttonContainer);
                }
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }
}


async function substituteDish(newDishId) {
    try {
        await fetch(url + "menu/substitute-dish?dailyDishId=" + dishForChangeId +"&newDishId=" + newDishId, {
            method: 'POST'
        });
    } catch (error) {
        console.error('Ошибка:', error);
    }
    document.location.reload();
}

