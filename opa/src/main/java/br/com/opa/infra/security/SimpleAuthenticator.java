package br.com.opa.infra.security;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.model.basic.User;

import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.service.usuario.UsuarioService;
import br.com.opa.util.UtilSeguranca;
import br.com.opa.entity.Usuario;
import br.com.opa.infra.message.MensagemFactory;

@Named
@PicketLink
public class SimpleAuthenticator extends BaseAuthenticator {
     
    private @Inject DefaultLoginCredentials credentials;
    private @Inject UsuarioService usuarioService;
    private @Inject CustomIdentity customIdentity;
    private @Inject MensagemFactory mensagemFactory;
    private UtilSeguranca utilSeguranca = new UtilSeguranca("MD5");
    private @Inject Identity identity;

    @Override
    public void authenticate() {
    	try{
    		if(customIdentity.getUsuario() != null && customIdentity.getUsuario().isAcessoPublico()){
    			Usuario usuario = usuarioService.login(customIdentity.getUsuario().getEmail(),customIdentity.getUsuario().getSenha());
    			setAccount(new User(usuario.getEmail()));
    			this.customIdentity.setUsuario(usuario);
    			this.customIdentity.setMenuCollapse(false);
    			setStatus(AuthenticationStatus.SUCCESS);
    		}else{
    			Usuario usuario = usuarioService.login(credentials.getUserId(),utilSeguranca.criptografa(credentials.getPassword()));
    			setAccount(new User(usuario.getEmail()));
    			this.customIdentity.setUsuario(usuario);
    			this.customIdentity.setMenuCollapse(false);
    			setStatus(AuthenticationStatus.SUCCESS);
    		}
    	}catch(Exception e){
    		setStatus(AuthenticationStatus.FAILURE);
    		String message = mensagemFactory.getMensagem(OpaMensagemEnum.ERROR_LOGIN.getKey());  
    		if(FacesContext.getCurrentInstance() != null){
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    		}
    	}  
    }
        
    public String logout() {
        identity.logout();
        this.customIdentity = null;
        //this.customIdentity.setUsuario(null);
        return "/opa/home";
    }

}