package br.com.opa.controller.usuario;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import br.com.opa.controller.principal.OpaController;
import br.com.opa.entity.Contratado;
import br.com.opa.entity.Usuario;
import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.enums.OpaNavegacaoEnum;
import br.com.opa.enums.PerfilUsuarioEnum;
import br.com.opa.enums.StatusContratadoEnum;
import br.com.opa.enums.StatusUsuarioEnum;
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
	
	public void cleanObjetc() throws Exception {
		getTo().setUsuario(new Usuario());
		getTo().setContratado(new Contratado());
	}

	private void recuperar(Integer id) throws Exception {
		getTo().setUsuario(usuarioService.recuperar(id));
	}
	
	public String abrirTelaCadastroUsuario() throws Exception {
		return OpaNavegacaoEnum.abrirCadastroUsuario.getName();
	}
	
	public String abrirTelaAlterarUsuario() throws Exception {
		getTo().setUsuario(usuarioService.recuperar(customIdentity.getUsuario().getId()));
		return OpaNavegacaoEnum.editarUsuario.getName();
	}
	
	public String capturarUsuario() throws Exception {
		getTo().setUsuario(usuarioService.recuperar(customIdentity.getUsuario().getId()));
		return OpaNavegacaoEnum.viewUsuario.getName();
	}
	
	public String capturarUsuarios() throws Exception {
		getTo().setUsuarios(usuarioService.pesquisar(getTo()));
		return OpaNavegacaoEnum.listarUsuario.getName();
	}
	
	public String incluirFormList() throws Exception {
		customIdentity.getUsuario();
		criptografarSenha(getTo().getUsuario().getSenha());
		definirDadosUsuario();
		definirDadosContratadoAPartirDoUsuario();
		usuarioService.incluir(getTo().getUsuario());
		incluirContratadoAPartirDoUsuarioSeNecessario();
		enviarEmailCadastroSucesso();
		limparObjetos();
		adicionarMensagemIncluirSucesso();
		getTo().setUsuarios(usuarioService.pesquisar(getTo()));
		return OpaNavegacaoEnum.listarUsuario.getName();
	}

	public String incluir() throws Exception {
		customIdentity.getUsuario();
		criptografarSenha(getTo().getUsuario().getSenha());
		definirDadosUsuario();
		definirDadosContratadoAPartirDoUsuario();
		usuarioService.incluir(getTo().getUsuario());
		incluirContratadoAPartirDoUsuarioSeNecessario();
		efetuarLogin();
		limparObjetos();
		adicionarMensagemIncluirSucesso();
		return null;
	}

	private void limparObjetos() throws Exception {
		getTo().setUsuario(new Usuario());
		getTo().setContratado(new Contratado());
	}

	private void efetuarLogin() throws Exception {
		if(!getTo().getUsuario().getEmail().isEmpty() && !getTo().getUsuario().getSenha().isEmpty()) {
			if(usuarioService.login(getTo().getUsuario().getEmail(), getTo().getUsuario().getSenha()) != null) {
				customIdentity.setUsuario(getTo().getUsuario());
				enviarEmailCadastroSucesso();
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void enviarEmailCadastroSucesso() throws Exception {
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("opawebmail@gmail.com", "opawebmail@!"));
		email.setSSL(true);
		email.setFrom("opa@opaweb.com");
		email.addTo(customIdentity.getUsuario().getEmail());
		email.setSubject("Opa! Cadastro Realizado com Sucesso!");
		email.setMsg("Parabéns " + customIdentity.getUsuario().getNome() + " ;) Agora você pode realizar cadastro de Ofertas! "
				+ "Obrigado!!");
		email.send();
	}

	private void incluirContratadoAPartirDoUsuarioSeNecessario() throws Exception {
		if(getTo().getUsuario().getPerfil().equals(PerfilUsuarioEnum.CONTRATADO)) {
			usuarioService.incluirContratadoAPartirDoUsuario(getTo().getContratado());
		}
	}

	private void definirDadosContratadoAPartirDoUsuario() throws Exception {
		getTo().getContratado().setEmail(getTo().getUsuario().getEmail());
		getTo().getContratado().setStatus(StatusContratadoEnum.A);
		getTo().getContratado().setDataCadastro(new Date());
	}

	private void definirDadosUsuario() throws Exception {
		getTo().getUsuario().setStatus(StatusUsuarioEnum.A);
		getTo().getUsuario().setDataCadastro(new Date());
	}

	public String alterar() throws Exception {
		definirDadosUsuarioParaAlteracao();
		usuarioService.alterar(getTo().getUsuario());
		adicionarMensagemAlteracaoSucesso();
		return OpaNavegacaoEnum.viewUsuario.getName();
	}

	private void definirDadosUsuarioParaAlteracao() throws Exception {
		getTo().getUsuario().setStatus(StatusUsuarioEnum.A);
		getTo().getUsuario().setEmail(customIdentity.getUsuario().getEmail());
		getTo().getUsuario().setPerfil(customIdentity.getUsuario().getPerfil());
		getTo().getUsuario().setDataCadastro(customIdentity.getUsuario().getDataCadastro());
		getTo().getUsuario().setDataAlteracao(new Date());
	}

	private void criptografarSenha(String senha) throws Exception{
		String senhaCriptografada = utilSeguranca.criptografa(getTo().getUsuario().getSenha());
		getTo().getUsuario().setSenha(senhaCriptografada);
	}

	public String excluir()throws Exception{
		usuarioService.excluirUsuario(getTo().getUsuario());
		adicionarMensagemExclusaoSucesso();
		return OpaNavegacaoEnum.listarUsuario.getName();
	}	

	public void alterarSenha() throws Exception {
		criptografarSenha(getTo().getUsuario().getSenha());
		usuarioService.alterar(getTo().getUsuario());
		adicionarMensagemSenhaAlteradaSucesso();
	}

	private void adicionarMensagemSenhaAlteradaSucesso() {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemFactory.getMensagem(OpaMensagemEnum.UC_USUARIO_SENHA_ALTERADA_SUCESSO.getKey()), 
				mensagemFactory.getMensagem(OpaMensagemEnum.UC_USUARIO_SENHA_ALTERADA_SUCESSO.getText()));
		FacesContext.getCurrentInstance().addMessage(null, m);
	}

}