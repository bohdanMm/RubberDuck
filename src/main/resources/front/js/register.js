window.onload = function () {
    setGenders();
    setTrainingRate();
    addNavigationMenu();
}


async function setGenders() {
    let select = document.querySelector("#gender");
    let genders = await getGenders();

    for (let i = 0; i < genders.length; i++) {
        let opt = document.createElement('option');
        opt.value = genders[i];
        opt.innerHTML = getDisplayNameForGenders(opt.value);
        select.appendChild(opt);
    }
}

async function setTrainingRate() {
    let select = document.querySelector("#trainingRate");
    let trainingRates = await getTrainingRates();

    for (let i = 0; i < trainingRates.length; i++) {
        let opt = document.createElement('option');
        opt.value = trainingRates[i];
        opt.innerHTML = getDisplayNameForTrainingRates(opt.value);
        select.appendChild(opt);
    }
}

function getDisplayNameForTrainingRates(name) {
    switch (name) {
        case "NO_TRAINING":
            return "Не тренуюся";
        case "RARE":
            return "Тренуюся 1-2 рази в тиждень";
        case "OFTEN":
            return "Тренуюся 3-5 разів в тиждень";
        case "INTENSIVE":
            return "Тренуюся 6-7 разів в тиждень";
        case "PROFESSIONAL":
            return "Тренуюся декілька разів на день";
    }
}

function getDisplayNameForGenders(name) {
    switch (name) {
        case "MALE":
            return "Чоловік";
        case "FEMALE":
            return "Жінка";
        case "OTHER":
            return "Не хочу вказувати";
    }
}


async function register() {
    let registerInfo = {
        email: document.querySelector('#email').value,
        password: document.querySelector('#password').value,
        fullName: document.querySelector('#fullName').value,
        age: document.querySelector('#age').value,
        gender: document.querySelector('#gender').value,
        height: document.querySelector('#height').value,
        weight: document.querySelector('#weight').value,
        trainingRate: document.querySelector('#trainingRate').value
    };

    try {
        await fetch(url + "main-user/register", {
            method: 'POST',
            body: JSON.stringify(registerInfo),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => window.location.href = "index.html");
    } catch (error) {
        console.error('Посилка:', error);
    }
}