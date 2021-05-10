async function getIngredients(objectId) {
    try {
        const response = await fetch(url + "ingredient/all", {
            method: 'GET'
        })
            .then(response => response.json())
            .then(json => {
                let select = document.getElementById(objectId);
                for (let i = 0; i < json.length; i++) {
                    let opt = document.createElement('option');
                    opt.value = json[i].id;
                    opt.innerHTML = json[i].name;
                    select.appendChild(opt);
                }
            });
    } catch (error) {
        console.error('Ошибка:', error);
    }
}