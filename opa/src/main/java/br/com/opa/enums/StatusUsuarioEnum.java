package br.com.opa.enums;

public enum StatusUsuarioEnum {

	A("label.global.ativo"),
	I("label.global.inativo"),
	E("label.global.excluido");
	
	private StatusUsuarioEnum(String key){
		setKey(key);
	}
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
