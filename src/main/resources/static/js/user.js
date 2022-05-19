async function getCurrentUser() {
    let currentUser = {};
    try {
        const response = await fetch("http://localhost:8090/api/user/get");

        currentUser = await response.json();
    } catch (error) {
        console.error('Ошибка:', error);
    }
    return currentUser;
}

function drawHeader(currentUser) {
    let content;
    const header = document.getElementById('header');
    content = `<a class="navbar-brand text-light bg-dark" >${currentUser.email} with roles: ${getRoleName(currentUser.roleSet)}</a>
                   <a class="navbar-brand left" href="/logout">Logout</a>`;
    header.innerHTML = content;
}

function drawCurrentUser(currentUser) {
    let content;
    const tableCurrentUser = document.getElementById('currentUserTable');
    content = `<tr>
                    <td>${currentUser.id}</td>
                    <td>${currentUser.name}</td>
                    <td>${currentUser.lastname}</td>
                    <td>${currentUser.email}</td>
                    <td>${currentUser.age}</td>
                    <td>${getRoleName(currentUser.roleSet)}</td>
                   </tr>`;
    tableCurrentUser.innerHTML = content;
}

function getRoleName(roles) {
    let role = "";

    for (let i = 0; i < roles.length; i++) {
        role += roles[i].name + ", ";
    }
    return role.substring(0, role.length - 2).replace(/ROLE_/g, '');
}

getCurrentUser().then(drawHeader);
getCurrentUser().then(drawCurrentUser)
