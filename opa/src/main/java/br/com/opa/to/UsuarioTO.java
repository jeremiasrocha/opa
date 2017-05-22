package br.com.opa.to;

import java.util.List;
import br.com.opa.entity.Usuario;

public class UsuarioTO /*extends NsaTO*/ {

    private Usuario usuario;
    private List<Usuario> usuarios;
    /*private List<Perfil> listaPerfil;*/

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}