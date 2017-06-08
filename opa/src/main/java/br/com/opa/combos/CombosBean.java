package br.com.opa.combos;

import javax.inject.Named;

import br.com.opa.enums.PerfilUsuarioEnum;

@Named
public class CombosBean {

	public PerfilUsuarioEnum[] getListaPerfil() throws Exception{
		return PerfilUsuarioEnum.values();
	}

	public PerfilUsuarioEnum[] getListaPerfilCadastro() throws Exception{
		PerfilUsuarioEnum[] perfis = new PerfilUsuarioEnum[2];
		perfis[0] = PerfilUsuarioEnum.CONTRATADO;
		perfis[1] = PerfilUsuarioEnum.COLABORADOR;
		return perfis;
	}

}