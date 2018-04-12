package br.com.opa.exception.oferta;

public class OfertaNaoEncontradoException extends OfertaException {

	private static final long serialVersionUID = -5507912631381022376L;

	public OfertaNaoEncontradoException() {
		super();
	}

	public OfertaNaoEncontradoException(String msg) {
		super(msg);
	}

}