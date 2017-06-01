package br.com.opa.enums;

public enum PerfilUsuarioEnum {
	
	GESTOR("label.perfil.code.usuario.gestor"),
	REVISOR("label.perfil.code.usuario.revisor"),
	CONTRATADO("label.perfil.code.usuario.contratado"),
	COLABORADOR("label.perfil.code.usuario.colaborador"),
	OPA("label.perfil.code.usuario.opa"),
	COMUM("label.perfil.code.usuario.comum");
	
	private PerfilUsuarioEnum(String text) {
		this.text = text;
	}

	private String text;

	public String getText() {
		return text;
	}
	
	public static PerfilUsuarioEnum getEnum(String name) {
		for (PerfilUsuarioEnum status : values()) {
			if (status.getText().equalsIgnoreCase(name)) {
				return status;
			}
		}
		return null;
	}
	
}