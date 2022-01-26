// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
});

async function RegistrarUsuarios() {
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    let repetirpassword = document.getElementById('txtRepetirPassword').value;

    if (repetirpassword != datos.password){
        alert('La contrasena que escribiste es diferente');
        return;
    }

    const request = await fetch('api/Usuarios', {
        method: 'POST',//AGREGAR DATOS
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert("La Cuenta Fue Creada Con Exito!");
    window.location.href = 'login.html';
    //const usuarios = await request.json();
    /*
    let ListadoHTML = '';
    for (let usuario of usuarios){
        let btnDelete = '<a href="#" onclick="ElminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></a>';
        let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+'</td><td>'+usuario.apellido+'</td><td>'+usuario.email+'</td>\n' +
            '    <td>'+usuario.telefono+'</td>\n' +
            '    <td>'+btnDelete+'</td></tr>';

        ListadoHTML += usuarioHTML;
    }

    //console.log(usuarios);

    document.querySelector('#usuarios tbody').outerHTML = ListadoHTML;*/
}
