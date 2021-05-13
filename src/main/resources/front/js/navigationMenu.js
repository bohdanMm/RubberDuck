function addNavigationMenu() {
    console.log("here")
    let element = document.querySelector("#navigationMenu");
    let user = JSON.parse(localStorage.getItem("user"));
    if (localStorage.getItem("user") === null) {
        fetch('../html/navigationMenu/unloginedNavigationMenu.html')
            .then(response => response.text())
            .then(text => {
                element.innerHTML = text;
            });
    } else if (user.role === "ADMIN") {
        console.log("here")
        fetch('../html/navigationMenu/adminNavigationMenu.html')
            .then(response => response.text())
            .then(text => {
                element.innerHTML = text;
            });
    } else if (user.role === "USER") {
        fetch('../html/navigationMenu/loginedUserNavigationMenu.html')
            .then(response => response.text())
            .then(text => {
                element.innerHTML = text;
            });
    }
}


function logOut() {
    localStorage.removeItem("user");
    window.location.href = "index.html";
}