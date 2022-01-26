package com.CursoJava.Demo.Controllers;

import com.CursoJava.Demo.Dao.UsuarioDao;
import com.CursoJava.Demo.Models.Usuario;
import com.CursoJava.Demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value = "api/Login",method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        Usuario usuarioLogueado = usuarioDao.ObetenerUsuarioPorCredencial(usuario);
        if(usuarioLogueado != null){

            String tokenjwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()),usuarioLogueado.getEmail());
            return tokenjwt;
        }
        return "FAIL";
    }
}
