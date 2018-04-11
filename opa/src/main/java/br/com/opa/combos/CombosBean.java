package br.com.opa.combos;

import javax.inject.Named;

import br.com.opa.enums.PerfilUsuarioEnum;
import br.com.opa.enums.StatusContratadoEnum;
import br.com.opa.enums.StatusUsuarioEnum;
import br.com.opa.enums.TipoContratadoEnum;
import br.com.opa.enums.TipoOfertaEnum;

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
	
	public StatusUsuarioEnum[] getListaStatusUsuario() throws Exception{
		return StatusUsuarioEnum.values();
	}
	
	public TipoContratadoEnum[] getListaTipoContratado() throws Exception{
		return TipoContratadoEnum.values();
	}
	
	public StatusContratadoEnum[] getListaStatusContratado() throws Exception{
		return StatusContratadoEnum.values();
	}
	
	public TipoOfertaEnum[] getListaTipoOferta() throws Exception{
		return TipoOfertaEnum.values();
	}

}