package br.com.opa.exception.contratado;

public class ContratadoNaoEncontradoException extends ContratadoException {

	private static final long serialVersionUID = 4752311089328015418L;

	public ContratadoNaoEncontradoException() {
		super();
	}

	public ContratadoNaoEncontradoException(String msg) {
		super(msg);
	}

}