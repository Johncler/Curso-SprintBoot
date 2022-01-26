// Call the dataTables jQuery plugin
$(document).ready(function() {
  CargarUsuarios();
  $('#usuarios').DataTable();
  actualizarEmailUsuario();
});

function actualizarEmailUsuario(){
  document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}
async function CargarUsuarios() {

  const request = await fetch('api/Usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();

  let ListadoHTML = '';
  for (let usuario of usuarios) {
    let fono = usuario.telefono == null ? '-' : usuario.telefono;
    let btnDelete = '<a href="#" onclick="ElminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></a>';
    let usuarioHTML = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + '</td><td>' + usuario.apellido + '</td><td>' + usuario.email + '</td>\n' +
        '    <td>' + fono + '</td>\n' +
        '    <td>' + btnDelete + '</td></tr>';

    ListadoHTML += usuarioHTML;
  }
  document.querySelector('#usuarios tbody').outerHTML = ListadoHTML;
}
function getHeaders() {
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token
  }
}
async function ElminarUsuario(id){
  if (!confirm('Desea eliminar este usuario')){
    return;
  }
  const request = await fetch('api/Usuario/' +id , {
    method: 'DELETE',
    headers: getHeaders()
  });
  location.reload();
}