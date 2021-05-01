function addNavigationMenu() {
    let element = document.querySelector("#navigationMenu");
    fetch('../html/loginedUserNavigationMenu.html')
        .then(response => response.text())
        .then(text => {
            element.innerHTML = text;
        });
}