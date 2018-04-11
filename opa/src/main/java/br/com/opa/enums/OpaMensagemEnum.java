package br.com.opa.enums;

public enum OpaMensagemEnum {
	
	//MENSAGENS GENÉRICAS
	INCLUIR_SUCESSO("label.msg.incluirSucesso","label.msg.incluirSucesso"),
	INCLUIR_ERRO("label.msg.incluirErro","label.msg.incluirErro"),
	ALTERAR_SUCESSO("label.msg.alterarSucesso","label.msg.alterarSucesso"),
	ALTERAR_ERRO("label.msg.alterarErro","label.msg.alterarErro"),
	EXCLUIR_SUCESSO("label.msg.excluirSucesso","label.msg.excluirSucesso"),
	EXCLUIR_ERRO("label.msg.excluirErro","label.msg.excluirErro"),
	ACESSO_NEGADO("label.msg.acessoNegado","label.msg.acessoNegado"),
	REGISTRO_JA_CADASTRADO("label.msg.registroJaCadastrado","label.msg.registroJaCadastrado"),

	//MENSAGENS USUÁRIO
	UC_USUARIO_SENHA_ALTERADA_SUCESSO("label.uc.usuario.msg.senhaAlteradaComSucesso","label.uc.usuario.msg.senhaAlteradaComSucesso"),
	UC_USUARIO_NAO_ENCONTRADO("label.uc.usuario.msg.usuarioNaoEncontrado","label.uc.usuario.msg.usuarioNaoEncontrado"),
	UC_USUARIO_LOGIN_JA_CADASTRADO("label.uc.usuario.msg.loginJaCadastrado","label.uc.usuario.msg.loginJaCadastrado"),
	ERROR_CONFIRM_LOGIN("label.uc.usuario.erro.senhaconfirmacao","label.uc.usuario.erro.senhaconfirmacao"),
	ERROR_LOGIN("label.uc.usuario.msg.ErroLogin","label.uc.usuario.msg.ErroLogin"),
	
	//MENSAGENS CONTRATADO
	UC_CONTRATADO_NAO_ENCONTRADO("label.uc.contratado.msg.contratadoNaoEncontrado","label.uc.contratado.msg.contratadoNaoEncontrado"),
	UC_CONTRATADO_JA_CADASTRADO("label.uc.contratado.msg.contratadoJaCadastrado","label.uc.contratado.msg.contratadoJaCadastrado"),	
	
	//MENSAGENS CATEGORIA
	UC_CATEGORIA_NAO_ENCONTRADA("label.uc.contratado.msg.contratadoNaoEncontrado","label.uc.contratado.msg.contratadoNaoEncontrado"),
	UC_CATEGORIA_JA_CADASTRADA("label.uc.contratado.msg.contratadoJaCadastrado","label.uc.contratado.msg.contratadoJaCadastrado"),
	
	//MENSAGENS OFERTA
	UC_OFERTA_NAO_ENCONTRADA("label.uc.oferta.msg.ofertaNaoEncontrada","label.uc.oferta.msg.ofertaNaoEncontrada"),
	UC_OFERTA_JA_CADASTRADA("label.uc.oferta.msg.ofertaJaCadastrada","label.uc.oferta.msg.ofertaJaCadastrada");
	
	private OpaMensagemEnum(String key,String text){
		this.key = key;
		this.text = text;
	}
	
	private String key;
	private String text;

	public String getText() {
		return text;
	}

	public String getKey() {
		return key;
	}

}