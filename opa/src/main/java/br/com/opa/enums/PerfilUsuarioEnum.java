package br.com.opa.enums;

public enum PerfilUsuarioEnum {

	
//    WHEN NOME_PERFIL_SAW='U' THEN V_NOME_PERFIL:='ATENDIMENTO'; ok
//    WHEN NOME_PERFIL_SAW='P' THEN V_NOME_PERFIL:='PRESTADOR'; ok
//    WHEN NOME_PERFIL_SAW='A' THEN V_NOME_PERFIL:='AUDITOR'; ok
//    WHEN NOME_PERFIL_SAW='C' THEN V_NOME_PERFIL:='EMPRESA'; ok
//    WHEN NOME_PERFIL_SAW='I' THEN V_NOME_PERFIL:='ESPECIALISTA'; ok
//    WHEN NOME_PERFIL_SAW='Z' THEN V_NOME_PERFIL:='PROFISSIONAL DE SAÚDE'; ok
//    WHEN NOME_PERFIL_SAW='M' THEN V_NOME_PERFIL:='ROOT';
//    WHEN NOME_PERFIL_SAW='S' THEN V_NOME_PERFIL:='ADMIN';
//    WHEN NOME_PERFIL_SAW='O' THEN V_NOME_PERFIL:='ANALISTA DE CONTAS';
//    WHEN NOME_PERFIL_SAW='B' THEN V_NOME_PERFIL:='BENEFICIÁRIO';
	
	ADMIN("label.global.admin"),
	ANALISTA_CONTAS("label.global.analistaContas"),
	ATENDIMENTO("label.global.atendimento"),
	AUDITOR("label.global.auditor"),
	BENEFICIARIO("label.global.beneficiario"),
	CONTRATADO("label.global.contratado"),
	EMPRESA("label.global.empresa"),
	ESPECIALISTA("label.global.especialista"),
	PROFISSIONAL_SAUDE("label.global.profissionalSaude"),
	ROOT("label.global.root"),
	ATENDENTE_PROTOCOLO("label.global.atendenteProtocolo"),
	AUDITOR_EXTERNO("label.global.auditorExterno");
	
	
	private PerfilUsuarioEnum(String key) {
		this.key = key;
	}
	
	private String key;

	public String getKey() {
		return key;
	}

	public String getName() {
		return name();
	}
	
	public static PerfilUsuarioEnum getEnum(String name) {
		for (PerfilUsuarioEnum status : values()) {
			if (status.getName().equalsIgnoreCase(name)) {
				return status;
			}
		}
		return null;
	}
}