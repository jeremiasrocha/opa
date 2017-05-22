package br.com.opa.enums;

public enum TipoIntervaloTempoEnum {

	HORA("label.dominio.intervaloHora"),
	DIA("label.dominio.intervaloDia"),
	MES("label.dominio.intervaloMes"),
	ANO("label.dominio.intervaloAno");

	private String key;

	private TipoIntervaloTempoEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name();
	}

}