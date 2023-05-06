package br.com.Security.DTO;

import br.com.Security.Model.Usuario;

public class UsuarioLogado {
    private static ThreadLocal<Usuario> usuarioThreadLocal = new ThreadLocal<>();

    public UsuarioLogado() {
    }

    public static void setUsuario(Usuario usuario) {
        usuarioThreadLocal.set(usuario);
    }

    public static Usuario getUsuario() {
        return usuarioThreadLocal.get();
    }


}
