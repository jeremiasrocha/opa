package br.com.opa.enums;

public enum OpaMensagemEnum {
	
	UC_TEMPLATE_ERRO_VALIDACAO_NOME("label.uc.template.erro.nome","label.uc.template.erro.nome"),
	ERROR_CONFIRM_LOGIN("label.uc.usuario.erro.senhaconfirmacao","label.uc.usuario.erro.senhaconfirmacao"),
	UC_USUARIO_SENHA_ALTERADA_SUCESSO("label.uc.usuario.msg.senhaAlteradaComSucesso","label.uc.usuario.msg.senhaAlteradaComSucesso"),
	ERROR_LOGIN("",""),
	UC_USUARIO_NAO_ENCONTRADO("",""),
	INCLUIR_SUCESSO("",""),
	REGISTRO_JA_CADASTRADO("",""),
	ALTERAR_SUCESSO("",""),
	EXCLUIR_SUCESSO("",""),
	EXCLUIR_ERRO("",""),
	ACESSO_NEGADO("",""),
	INCLUIR_ERRO("",""),
	ALTERAR_ERRO("",""),
	UC_USUARIO_LOGIN_JA_CADASTRADO("","");
	
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