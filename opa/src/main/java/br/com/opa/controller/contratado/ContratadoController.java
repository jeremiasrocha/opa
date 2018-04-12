package br.com.opa.controller.contratado;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.opa.controller.principal.OpaController;
import br.com.opa.enums.OpaNavegacaoEnum;
import br.com.opa.service.contratado.ContratadoService;
import br.com.opa.to.ContratadoTO;

@Model
public class ContratadoController extends OpaController<ContratadoTO> {

	private static final long serialVersionUID = 485932261954303695L;
	
	private @Inject ContratadoService contratadoService;

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
	
	public String abrirTelaParametrosContratado() throws Exception {
		return OpaNavegacaoEnum.abrirParametrosContratado.getName();
	}
	
	public String capturarContratados() throws Exception {
		getTo().setContratados(contratadoService.pesquisar(getTo()));
		return OpaNavegacaoEnum.listarContratado.getName();
	}

	private void recuperar(Integer id) throws Exception {
		getTo().setContratado(contratadoService.recuperar(id));
	}

	public String incluir() throws Exception {
		definirDadosContratado();
		contratadoService.incluir(getTo().getContratado());
		adicionarMensagemIncluirSucesso();
		return null;
	}

	private void definirDadosContratado() throws Exception {
	}

	public String alterar() throws Exception {
		contratadoService.alterar(getTo().getContratado());
		adicionarMensagemAlteracaoSucesso();
		return null;
	}

	public String excluir()throws Exception{
		contratadoService.excluirContratado(getTo().getContratado());
		adicionarMensagemExclusaoSucesso();
		return OpaNavegacaoEnum.listarContratado.getName();
	}	
}