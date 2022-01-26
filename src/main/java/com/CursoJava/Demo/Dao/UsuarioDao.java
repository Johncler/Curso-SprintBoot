package com.CursoJava.Demo.Dao;

import com.CursoJava.Demo.Models.Usuario;
import com.sun.istack.NotNull;


import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    void elimiar(Long id);

    void registrar(Usuario usuario);
    Usuario ObetenerUsuarioPorCredencial(Usuario usuario);
}
