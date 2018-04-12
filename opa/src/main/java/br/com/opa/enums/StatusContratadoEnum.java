package br.com.opa.enums;

public enum StatusContratadoEnum {

	A("label.global.ativo"),
	I("label.global.inativo"),
	E("label.global.excluido");
	
	private StatusContratadoEnum(String text){
		this.text = text;
	}
	
	private String text;

	public String getText() {
		return text;
	}
	
}
