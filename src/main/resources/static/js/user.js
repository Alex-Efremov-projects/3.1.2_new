// Populates single table row with user's data and displays Admin Panel link
const populateSingleRowTable = () => {
    fetch('http://localhost:8090/api/user')
        .then(response => {
            response.json().then(user => {
                document.querySelector("#tableId").innerHTML = user.id;
                document.querySelector("#tableName").innerHTML = user.name;
                document.querySelector("#tableLastName").innerHTML = user.lastname;
                document.querySelector("#tableEmail").innerHTML = user.email;
                document.querySelector("#tableAge").innerHTML = user.age;
                document.querySelector("#tableRoles").innerHTML = document.querySelector("#headerRole").innerHTML;
                // Displaying Admin Panel link
                if (document.querySelector("#headerRole").text.includes("ROLE_ADMIN")) {
                    document.querySelector("#admin").style.display = "block";
                }
            })
        })
}
populateSingleRowTable()