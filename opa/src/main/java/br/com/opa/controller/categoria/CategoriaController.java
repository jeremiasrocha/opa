package br.com.opa.controller.categoria;


import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.opa.controller.principal.OpaController;
import br.com.opa.enums.OpaNavegacaoEnum;
import br.com.opa.service.categoria.CategoriaService;
import br.com.opa.to.CategoriaTO;

@Model
public class CategoriaController extends OpaController<CategoriaTO> {

	private static final long serialVersionUID = 3843242332502167201L;
	
	private @Inject CategoriaService categoriaService;

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
	
	public String abrirTelaCadastroCategoria() throws Exception {
		return OpaNavegacaoEnum.abrirCadastroCategoria.getName();
	}

	private void recuperar(Integer id) throws Exception {
		getTo().setCategoria(categoriaService.recuperar(id));
	}

	public String incluir() throws Exception {
		definirDadosCategoria();
		categoriaService.incluir(getTo().getCategoria());
		adicionarMensagemIncluirSucesso();
		return null;
	}
	
	public String capturarCategorias() throws Exception {
		getTo().setCategorias(categoriaService.pesquisar(getTo()));
		return OpaNavegacaoEnum.listarCategoria.getName();
	}

	private void definirDadosCategoria() throws Exception {
		getTo().getCategoria().setDataCadastro(new Date());
	}

	public String alterar() throws Exception {
		categoriaService.alterar(getTo().getCategoria());
		adicionarMensagemAlteracaoSucesso();
		return null;
	}

	public String excluir()throws Exception{
		categoriaService.excluirCategoria(getTo().getCategoria());
		adicionarMensagemExclusaoSucesso();
		return OpaNavegacaoEnum.listarUsuario.getName();
	}	
	
}