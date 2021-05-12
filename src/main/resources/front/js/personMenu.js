function getMenu(li) {
}

function addElementToList(li) {
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