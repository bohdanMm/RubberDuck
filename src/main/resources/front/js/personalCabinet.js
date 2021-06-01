window.onload = function () {
    setGenders();
    setTrainingRate();
    addNavigationMenu();
    getUser();
}

async function getUser() {
    try {
        let user = JSON.parse(localStorage.getItem("user"));
        await fetch(url + "main-user/" + user.id , {
            method: 'GET'
        })
            .then(response => response.json())
            .then(json => {
                document.querySelector("#email").value = json.email;
                document.querySelector("#fullName").value = json.fullName;
                document.querySelector("#age").value = json.age;
                document.querySelector("#height").value = json.height;
                document.querySelector("#weight").value = json.weight;
                document.querySelector("#dailyCalories").value = json.dailyCalories;
                selectElement("age", json.age);
                selectElement("trainingRate", json.trainingRate);
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }

    function selectElement(id, valueToSelect) {
        let element = document.getElementById(id);
        element.value = valueToSelect;
    }
}

async function editMainUser() {
    let editMainUserDto = {
        email: document.querySelector('#email').value,
        fullName: document.querySelector('#fullName').value,
        age: document.querySelector('#age').value,
        gender: document.querySelector('#gender').value,
        height: document.querySelector('#height').value,
        weight: document.querySelector('#weight').value,
        trainingRate: document.querySelector('#trainingRate').value
    };

    try {
        let user = JSON.parse(localStorage.getItem("user"));
        await fetch(url + "main-user/edit/" + user.id, {
            method: 'PUT',
            body: JSON.stringify(editMainUserDto),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => document.location.reload());
    } catch (error) {
        console.error('Посилка:', error);
    }
}

async function changePassword() {
    let editPassword = {
        oldPassword: document.querySelector('#currentPassword').value,
        newPassword: document.querySelector('#newPassword').value
    };
    console.log(editPassword)
    try {
        let user = JSON.parse(localStorage.getItem("user"));
        await fetch(url + "main-user/edit/password/" + user.id, {
            method: 'PUT',
            body: JSON.stringify(editPassword),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => document.location.reload());
    } catch (error) {
        console.error('Посилка:', error);
    }
}

