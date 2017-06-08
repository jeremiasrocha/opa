package br.com.opa.enums;

public enum PerfilUsuarioEnum {
	
	GESTOR("label.perfil.usuario.gestor"),
	REVISOR("label.perfil.usuario.revisor"),
	CONTRATADO("label.perfil.usuario.contratado"),
	COLABORADOR("label.perfil.usuario.colaborador"),
	OPA("label.perfil.usuario.opa"),
	COMUM("label.perfil.usuario.comum");
	
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