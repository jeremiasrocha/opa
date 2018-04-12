package br.com.opa.controller.oferta;


import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.opa.controller.principal.OpaController;
import br.com.opa.enums.OpaNavegacaoEnum;
import br.com.opa.enums.StatusOfertaEnum;
import br.com.opa.service.oferta.OfertaService;
import br.com.opa.to.CategoriaTO;
import br.com.opa.to.OfertaTO;

@Model
public class OfertaController extends OpaController<OfertaTO> {

	private static final long serialVersionUID = 4234766118545013142L;
	private @Inject OfertaService ofertaService;

	@PostConstruct
	private void inicializar(){
		try{
			if(getRequest().getParameter("parametro") != null) {
	 			if (getRequest().getParameter("parametro").equals("new")){
	 			} else if (getRequest().getParameter("parametro").equals("edit")){
	 				recuperar(Integer.valueOf(getRequest().getParameter("id")));
	 			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	private void recuperar(Integer id) throws Exception {
		getTo().setOferta(ofertaService.recuperar(id));
	}
	
	public String abrirTelaCadastroOferta() throws Exception {
		CategoriaTO categoria = new CategoriaTO();
		getTo().setCategorias(ofertaService.capturarCategorias(categoria));
		return OpaNavegacaoEnum.abrirCadastroOferta.getName();
	}
	
	public String capturarOfertasUsuario() throws Exception {
		getTo().getOferta().setIdUsuario((customIdentity.getUsuario().getId()));
		getTo().setIdUsuario((customIdentity.getUsuario().getId()));
		ofertaService.capturarOfertasUsuario(getTo());
		return OpaNavegacaoEnum.listarOferta.getName();
	}
	
	public String incluir() throws Exception {
		definirDadosOferta();
		ofertaService.incluir(getTo().getOferta());
		adicionarMensagemIncluirSucesso();
		return null;
	}

	private void definirDadosOferta() throws Exception {
		getTo().getOferta().setIdUsuario(customIdentity.getUsuario().getId());
		getTo().getOferta().setDataCadastro(new Date());
		getTo().getOferta().setStatus(StatusOfertaEnum.A);
	}

	public String alterar() throws Exception {
		ofertaService.alterar(getTo().getOferta());
		adicionarMensagemAlteracaoSucesso();
		return null;
	}

	public String excluir()throws Exception{
		ofertaService.excluirOferta(getTo().getOferta());
		adicionarMensagemExclusaoSucesso();
		return OpaNavegacaoEnum.listarOferta.getName();
	}	

}