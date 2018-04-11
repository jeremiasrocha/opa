package br.com.opa.enums;

public enum OpaNavegacaoEnum {
	
	abrirCadastroUsuario,
	abrirParametrosContratado,
	abrirCadastroOferta,
	abrirCadastroCategoria,
	
	listarUsuario,
	listarContratado,
	listarOferta,
	listarCategoria,
	
	editarUsuario,
	editarParametrosContratado,
	editarOferta,
	editarCategoria,
	
	viewUsuario,
	viewParametrosContratado,
	viewOferta,
	viewCategoria;
	
	public String getName(){
		return name();
	}

}
