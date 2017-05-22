package br.com.opa.controller.usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.opa.controller.principal.OpaController;
import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.enums.OpaNavegacaoEnum;
import br.com.opa.infra.message.MensagemFactory;
import br.com.opa.infra.security.CustomIdentity;
import br.com.opa.service.usuario.UsuarioService;
import br.com.opa.to.UsuarioTO;
import br.com.opa.util.UtilSeguranca;

@Model
public class UsuarioController extends OpaController<UsuarioTO> {
	
	private static final long serialVersionUID = 9157011893716654515L;
	
	private @Inject UsuarioService usuarioService;
	private @Inject CustomIdentity customIdentity;
	private @Inject MensagemFactory mensagemFactory;
	private UtilSeguranca utilSeguranca = new UtilSeguranca("MD5");

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
		getTo().setUsuario(usuarioService.recuperar(id));
	}

	public String incluir() throws Exception {
		criptografarSenha(getTo().getUsuario().getSenha());
		usuarioService.incluir(getTo().getUsuario());
		adicionarMensagemIncluirSucesso();
		return null;
	}

	public String alterar() throws Exception {
		usuarioService.alterar(getTo().getUsuario());
		adicionarMensagemAlteracaoSucesso();
		return null;
	}

	private void criptografarSenha(String senha) throws Exception{
		String senhaCriptografada = utilSeguranca.criptografa(getTo().getUsuario().getSenha());
		getTo().getUsuario().setSenha(senhaCriptografada);
	}

	public String excluir()throws Exception{
		usuarioService.excluirUsuario(getTo().getUsuario());
		adicionarMensagemExclusaoSucesso();
		return OpaNavegacaoEnum.listar.getName();
	}	

	public void alterarSenha() throws Exception {
		criptografarSenha(getTo().getUsuario().getSenha());
		usuarioService.alterar(getTo().getUsuario());
		adicionarMensagemSenhaAlteradaSucesso();
	}

	/*private boolean isPerfilContratadoSelecionado() throws Exception {
		return getTo().getListaPerfil().stream().anyMatch(p -> p.getNome().equals(PerfilUsuarioEnum.CONTRATADO.getName())
				|| (!p.getImutavel() && p.getPerfilBase().equals(PerfilUsuarioEnum.CONTRATADO.getName())));
	}

	private boolean isPerfilProfissionalSaudeSelecionado() throws Exception {
		return getTo().getListaPerfil().stream().anyMatch(p -> p.getNome().equals(PerfilUsuarioEnum.PROFISSIONAL_SAUDE.getName())
				|| (!p.getImutavel() && p.getPerfilBase().equals(PerfilUsuarioEnum.PROFISSIONAL_SAUDE.getName())));
	}

	private boolean isPerfilEmpresaSelecionado() throws Exception {
		return getTo().getListaPerfil().stream().anyMatch(p -> p.getNome().equals(PerfilUsuarioEnum.EMPRESA.getName())
				|| (!p.getImutavel() && p.getPerfilBase().equals(PerfilUsuarioEnum.EMPRESA.getName())));
	}

	private boolean isPerfilAuditorSelecionado() throws Exception {
		return getTo().getListaPerfil().stream().anyMatch(p -> p.getNome().equals(PerfilUsuarioEnum.AUDITOR.getName())
				|| (!p.getImutavel() && p.getPerfilBase().equals(PerfilUsuarioEnum.AUDITOR.getName())));
	}

	private boolean isPerfilEspecialistaSelecionado() throws Exception {
		return getTo().getListaPerfil().stream().anyMatch(p -> p.getNome().equals(PerfilUsuarioEnum.ESPECIALISTA.getName())
				|| (!p.getImutavel() && p.getPerfilBase().equals(PerfilUsuarioEnum.ESPECIALISTA.getName())));
	}

	private boolean isPerfilAtendimentoSelecionado() throws Exception {
		return getTo().getListaPerfil().stream().anyMatch(p -> p.getNome().equals(PerfilUsuarioEnum.ATENDIMENTO.getName())
						|| (!p.getImutavel() && p.getPerfilBase().equals(PerfilUsuarioEnum.ATENDIMENTO.getName())));
	}*/

	private void adicionarMensagemSenhaAlteradaSucesso() {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemFactory.getMensagem(OpaMensagemEnum.UC_USUARIO_SENHA_ALTERADA_SUCESSO.getKey()), 
				mensagemFactory.getMensagem(OpaMensagemEnum.UC_USUARIO_SENHA_ALTERADA_SUCESSO.getText()));
		FacesContext.getCurrentInstance().addMessage(null, m);
	}

}