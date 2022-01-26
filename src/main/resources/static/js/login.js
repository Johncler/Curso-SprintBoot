// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
});

async function IniciarSesion() {
    let datos = {};
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    const request = await fetch('api/Login', {
        method: 'POST',//AGREGAR DATOS
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const respuesta = await request.text();
    if (respuesta != 'FAIL'){
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'usuario.html';
    }else{
        alert("Las credenciales son Incorrectas. Por favor intente nuevamente");
    }
}