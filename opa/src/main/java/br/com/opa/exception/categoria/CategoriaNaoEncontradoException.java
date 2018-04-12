package br.com.opa.exception.categoria;

public class CategoriaNaoEncontradoException extends CategoriaException {

	private static final long serialVersionUID = -8586045766479022529L;

	public CategoriaNaoEncontradoException() {
		super();
	}

	public CategoriaNaoEncontradoException(String msg) {
		super(msg);
	}

}