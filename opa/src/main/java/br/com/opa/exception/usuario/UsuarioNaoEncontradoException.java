package br.com.opa.exception.usuario;

public class UsuarioNaoEncontradoException extends UsuarioException {

	private static final long serialVersionUID = -2260722869849112806L;

	public UsuarioNaoEncontradoException() {
		super();
	}

	public UsuarioNaoEncontradoException(String msg) {
		super(msg);
	}

}