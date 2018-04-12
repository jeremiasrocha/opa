package br.com.opa.enums;

public enum StatusOfertaEnum {

	A("label.uc.oferta.statusAtivo"),
	N("label.uc.oferta.statusNegado"),
	E("label.uc.oferta.statusExpirado"),
	P("label.uc.oferta.statusPendente"),
	H("label.uc.oferta.statusAguardando");
	
	private StatusOfertaEnum(String text){
		this.text = text;
	}
	
	private String text;

	public String getText() {
		return text;
	}
	
}
