// Populates the header
const populateHeader = () => {
    fetch('http://localhost:8090/api/user')
        .then(response => {
            response.json().then(user => {
                let roles = "";
                for (const role of user.roleSet) {
                    roles += role.name + " ";
                }
                document.querySelector("#headerName").text = user.name;
                document.querySelector("#headerRole").text = roles;
            })
        })
}
populateHeader()