package br.com.opa.enums;

public enum StatusUsuarioEnum {

	A("label.global.ativo"),
	I("label.global.inativo"),
	E("label.global.excluido");
	
	private StatusUsuarioEnum(String text){
		this.text = text;
	}
	
	private String text;

	public String getText() {
		return text;
	}
	
}
