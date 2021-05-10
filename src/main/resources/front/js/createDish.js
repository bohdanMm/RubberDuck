let counter;

window.onload = function () {
    addNavigationMenu();
    counter = 0;
}


function addIngredient(){
    let buttonLi = document.querySelector("#addIngredientButtonLi");
    let li = document.createElement("li");
    let select = document.createElement("select");
    select.id = "select" + counter;
    li.append(select);
    buttonLi.before(li);
    getIngredients(select.id);
    counter++;

}