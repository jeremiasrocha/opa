package br.com.opa.infra.security;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import br.com.opa.entity.Usuario;

@Named
@SessionScoped
public class CustomIdentity implements Serializable {

	private static final long serialVersionUID = -8517992615969053298L;
	private Usuario usuario;
	private boolean menuCollapse = false;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isMenuCollapse() {
		return menuCollapse;
	}

	public void setMenuCollapse(boolean menuCollapse) {
		this.menuCollapse = menuCollapse;
	}

}
