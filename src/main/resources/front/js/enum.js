async function getGenders() {
    try {
        const response = await fetch(url + "enum/genders", {
            method: 'GET'
        });
        const json = await response.json();
        return json;
    } catch (error) {
        console.error('Ошибка:', error);
    }
}

async function getTrainingRates() {
    try {
        const response = await fetch(url + "enum/training-rates", {
            method: 'GET'
        });
        return await response.json();
    } catch (error) {
        console.error('Ошибка:', error);
    }
}