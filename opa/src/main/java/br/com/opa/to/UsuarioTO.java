package br.com.opa.to;

import java.util.ArrayList;
import java.util.List;

import br.com.opa.entity.Usuario;

public class UsuarioTO {

    private Usuario usuario;
    private String confirmarSenha;
    private List<Usuario> usuarios;
    
    public String getConfirmarSenha() {
    	if (confirmarSenha == null) {
			confirmarSenha = new String();
		}
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

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
		if (usuarios == null) {
			usuarios = new ArrayList<>();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}