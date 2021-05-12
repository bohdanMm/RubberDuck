window.onload = function () {
    addNavigationMenu();
    counter = 0;
}

async function createIngredient() {
    let ingredient = {
        name: document.querySelector('#name').value,
        calories: document.querySelector('#calories').value
    };

    try {
        await fetch(url + "ingredient/create", {
            method: 'POST',
            body: JSON.stringify(ingredient),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    } catch (error) {
        console.error('Помилка:', error);
    }
}