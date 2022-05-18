async function getUsers() {
    let allUsers = {};

    try {
        const response = await fetch("http://localhost:8090/api/admin");

        allUsers = await response.json();
    } catch (error) {
        console.error('Ошибка:', error);
    }

    return allUsers;
}

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

async function addNewUser(newUser) {
    const url = 'http://localhost:8090/api/admin/save';
    console.log(newUser);
    try {
        const response = await fetch(url, {
            method: 'POST',
            body: newUser,
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const json = await response.json();
        console.log('Успех:', JSON.stringify(json));
    } catch (error) {
        console.error('Ошибка:', error);
    }

}

async function putEditUser(editUser, id) {
    const url = 'http://localhost:8090/api/admin/update/' + id;

    try {
        const response = await fetch(url, {
            method: 'PATCH',
            body: editUser,
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const json = await response.json();
        console.log('Успех:', JSON.stringify(json));
    } catch (error) {
        console.error('Ошибка:', error);
    }
    getUsers().then(drawTable);

}

async function deleteUserDB(id) {
    const url = 'http://localhost:8090/api/admin/delete/' + id;

    try {
        await fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        console.log('Delete user id = ' + id);
    } catch (error) {
        console.error('Ошибка:', error);
    }
    getUsers().then(drawTable);

}

function drawHeader(currentUser) {
    let content;
    const header = document.getElementById('header');
    content = `<a class="navbar-brand text-light bg-dark" >${currentUser.email} with roles: ${getRoleName(currentUser.roleSet)}</a>
                   <a class="navbar-brand left" href="/logout">Logout</a>`;
    header.innerHTML = content;
}

function drawCurrentUser(currentUser) {
    let content = '';
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


async function getAddUser() {
    const name = document.getElementById('firstNameAdd').value;
    const lastname = document.getElementById('lastNameAdd').value;
    const age = document.getElementById('ageAdd').value;
    const email = document.getElementById('emailAdd').value;
    const password = document.getElementById('passwordAdd').value;
    const roleSet = getRolesJSON(document.getElementById('idRoleAdd'));

    try {
        await addNewUser(JSON.stringify({name, lastname, age, email, password, roleSet}, id));

        $('.nav-tabs a[href="#home"]').tab('show');
    } catch (error) {
    }
}

async function getEditUser() {
    const id = document.getElementById('idEdit').value;
    const name = document.getElementById('firstNameEdit').value;
    const lastname = document.getElementById('lastNameEdit').value;
    const age = document.getElementById('ageEdit').value;
    const email = document.getElementById('emailEdit').value;
    const password = document.getElementById('passwordEdit').value;
    const roleSet = getRolesJSON(document.getElementById('idRoleEdit'));

    try {
        await putEditUser(JSON.stringify({name, lastname, age, email, password, roleSet}), id);

        $('.nav-tabs a[href="#home"]').tab('show');
    } catch (error) {
    }
}

function drawTable(allUsers) {
    const table = document.getElementById('myTable');
    let tableContent = '';

    for (let i = 0; i < allUsers.length; i++) {
        tableContent += `<tr>
                        <td>${allUsers[i].id}</td>
                        <td>${allUsers[i].name}</td>
                        <td>${allUsers[i].lastname}</td>
                        <td>${allUsers[i].email}</td>
                        <td>${allUsers[i].age}</td>
                        <td>${getRoleName(allUsers[i].roleSet)}</td>
                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#editModal"  data-whatever="${i}">
                                Edit
                            </button></td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal"
                                data-target="#deleteModal"  data-whatever="${i}">
                                Delete
                            </button>
                        </td>
                    </tr>`;

    }
    table.innerHTML = tableContent;

}

function getRoleName(roles) {
    let role = "";

    for (let i = 0; i < roles.length; i++) {
        role += roles[i].name + ", ";
    }
    return role.substring(0, role.length - 2).replace(/ROLE_/g, '');
}

function getRolesJSON(roles) {
    let massRoles = [];

    function objRole(id, name) {
        this.id = id;
        this.name = name;
    }

    for (let i = 0; i < roles.options.length; i++) {
        if (roles.options[i].selected) {
            massRoles.push(new objRole(roles.options[i].value, roles.options[i].text));
        }
    }

    return massRoles;
}

function clearDataNewUser() {
    $('.new-user-body #firstNameAdd').val('');
    $('.new-user-body #lastNameAdd').val('');
    $('.new-user-body #ageAdd').val('');
    $('.new-user-body #emailAdd').val('');
    $('.new-user-body #passwordAdd').val('');
    $('.new-user-body #idRoleAdd option').prop('selected', false);

}

function deleteUser() {
    const idDeleteUser = document.getElementById('buttonDelete').value;
    deleteUserDB(idDeleteUser).then();
}

getUsers().then(drawTable);
getCurrentUser().then(drawHeader);

$('.nav-tabs a[href="#home"]').on('show.bs.tab', () => getUsers().then(drawTable));
$('.nav-tabs a[href="#newUser"]').on('show.bs.tab', () => clearDataNewUser());
$('.nav-pills a[href="#v-pills-profile"]').on('show.bs.tab', () => getCurrentUser().then(drawCurrentUser));
$('#deleteModal').on('show.bs.modal', async function (event) {
    const button = $(event.relatedTarget); // Button that triggered the modal
    const i = button.data('whatever'); // Extract info from data-* attributes
    let userDelete = await getUsers();

    const modal = $(this);
    modal.find(".modal-delete-body #idDelete").val(userDelete[i].id);
    modal.find(".modal-delete-body #firstNameDelete").val(userDelete[i].name);
    modal.find(".modal-delete-body #lastNameDelete").val(userDelete[i].lastname);
    modal.find(".modal-delete-body #ageDelete").val(userDelete[i].age);
    modal.find(".modal-delete-body #emailDelete").val(userDelete[i].email);
    modal.find('.modal-delete-body #idRoleDelete option').prop('selected', false);
    for (let j = 0; j < userDelete[i].roleSet.length; j++) {
        modal.find('.modal-delete-body #idRoleDelete option[value="' + userDelete[i].roleSet[j].id + '"]').prop('selected', true);
    }
    modal.find(".modal-delete-body #buttonDelete").val(userDelete[i].id);

});
$('#editModal').on('show.bs.modal', async function (event) {
    const button = $(event.relatedTarget); // Button that triggered the modal
    const i = button.data('whatever'); // Extract info from data-* attributes
    let userEdit = await getUsers();

    const modal = $(this);
    modal.find(".modal-edit-body #idEdit").val(userEdit[i].id);
    modal.find(".modal-edit-body #firstNameEdit").val(userEdit[i].name);
    modal.find(".modal-edit-body #lastNameEdit").val(userEdit[i].lastname);
    modal.find(".modal-edit-body #ageEdit").val(userEdit[i].age);
    modal.find(".modal-edit-body #emailEdit").val(userEdit[i].email);
    modal.find('.modal-edit-body #idRoleEdit option').prop('selected', false);
    for (let j = 0; j < userEdit[i].roleSet.length; j++) {
        modal.find('.modal-edit-body #idRoleEdit option[value="' + userEdit[i].roleSet[j].id + '"]').prop('selected', true);
    }
    modal.find(".modal-edit-body #buttonEdit").val(userEdit[i].id);

});

document.getElementById('addUser').addEventListener('click', getAddUser);
document.getElementById('buttonDelete').addEventListener('click', deleteUser);
document.getElementById('buttonEdit').addEventListener('click', getEditUser);