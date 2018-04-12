package br.com.opa.enums;

public enum TipoOfertaEnum {

	LV("label.uc.oferta.tipoLojaVirtual"),
	LF("label.uc.oferta.tipoLojaFisica");
	
	private TipoOfertaEnum(String text){
		this.text = text;
	}
	
	private String text;

	public String getText() {
		return text;
	}
	
}
