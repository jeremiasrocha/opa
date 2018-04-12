package br.com.opa.enums;

public enum TipoContratadoEnum {

	PF("label.global.pessoaFisica"),
	PJ("label.global.pessoaJuridica");
	
	private TipoContratadoEnum(String text){
		this.text = text;
	}
	
	private String text;

	public String getText() {
		return text;
	}
	
}
