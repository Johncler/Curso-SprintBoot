package com.CursoJava.Demo.Controllers;

import com.CursoJava.Demo.Dao.UsuarioDao;
import com.CursoJava.Demo.Models.Usuario;
import com.CursoJava.Demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    //hace que automaticamente la clase usuario implente crre un objeto y guarde en usuarioDao
    // "SE LLAMA INYECION DE DEPENDENCIAS"
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value = "api/Usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("John clever");
        usuario.setApellido("Choque Aranibar");
        usuario.setEmail("john!gmai;.com");
        usuario.setTelefono("123456");
        return usuario;
    }
    @RequestMapping(value = "api/Usuarios",method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {
        /*List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        usuario1.setId(100l);
        usuario1.setNombre("Kvein fernandes");
        usuario1.setApellido("Aravire Gonzales");
        usuario1.setEmail("kevi@gmai;.com");
        usuario1.setTelefono("0000001");

        Usuario usuario2 = new Usuario();
        usuario2.setId(101l);
        usuario2.setNombre("Kvein fernandes2");
        usuario2.setApellido("Aravire Gonzales2");
        usuario2.setEmail("kevin2@gmai;.com");
        usuario2.setTelefono("0000002");

        Usuario usuario3 = new Usuario();
        usuario3.setId(103l);
        usuario3.setNombre("Kvein fernandes3");
        usuario3.setApellido("Aravire Gonzales3");
        usuario3.setEmail("kevin3@gmai;.com");
        usuario3.setTelefono("0000003");

        Usuario usuario4 = new Usuario();
        usuario4.setId(104l);
        usuario4.setNombre("Kvein fernandes4");
        usuario4.setApellido("Aravire Gonzales4");
        usuario4.setEmail("kevin4@gmai;.com");
        usuario4.setTelefono("0000004");

        Usuario usuario5 = new Usuario();
        usuario5.setId(105l);
        usuario5.setNombre("Kvein fernandes5");
        usuario5.setApellido("Aravire Gonzales5");
        usuario5.setEmail("kevin5@gmai;.com");
        usuario5.setTelefono("0000005");

        Usuario usuario6 = new Usuario();
        usuario6.setId(106l);
        usuario6.setNombre("Kvein fernandes6");
        usuario6.setApellido("Aravire Gonzales6");
        usuario6.setEmail("kevin5@gmai;.com");
        usuario6.setTelefono("0000006");

        Usuario usuario7 = new Usuario();
        usuario7.setId(107l);
        usuario7.setNombre("Kvein fernandes7");
        usuario7.setApellido("Aravire Gonzales7");
        usuario7.setEmail("kevin7@gmai;.com");
        usuario7.setTelefono("0000007");

        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);
        usuarios.add(usuario5);
        usuarios.add(usuario6);
        usuarios.add(usuario7);
        return usuarios;*/ //Forma manual
        //Inyeccion de dependencia desde la BDD
        if (!ValidarToken(token)){
            return null;
        }
        return usuarioDao.getUsuarios();
    }
    private boolean ValidarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;

    }
    @RequestMapping(value = "api/Usuarios",method = RequestMethod.POST)
    public void RegistrarUsuarios(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }
    //Eliminar
    @RequestMapping(value = "api/Usuario/{id}",method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token,@PathVariable Long id) {
        if (!ValidarToken(token)){
            return ;
        }
        usuarioDao.elimiar(id);
    }

}
