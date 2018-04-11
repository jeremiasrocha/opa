package br.com.opa.service.usuario;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.opa.dao.OpaDataAbstract;
import br.com.opa.dao.usuario.UsuarioDAO;
import br.com.opa.entity.Contratado;
import br.com.opa.entity.Usuario;
import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.exception.usuario.UsuarioException;
import br.com.opa.service.ServiceAbstract;
import br.com.opa.service.contratado.ContratadoService;
import br.com.opa.to.FiltroTO;
import br.com.opa.to.UsuarioTO;

@Stateless
public class UsuarioService extends ServiceAbstract<Usuario> {

	private @Inject UsuarioDAO usuarioDAO;
	private @Inject ContratadoService contratadoService;

	@Override
	protected OpaDataAbstract<Usuario> getEntityBean() {
		return usuarioDAO;
	}

	public void incluirContratadoAPartirDoUsuario(Contratado contratado) throws Exception {
		contratadoService.incluir(contratado);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario login(String userId, String password) throws Exception {
		Usuario usuario = usuarioDAO.login(userId,password);
		recuperarParametros(usuario);
		return usuario;
	}

	private void recuperarParametros(Usuario usuario) {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario recuperarCompletoPorEmail(String email) throws Exception {
		Usuario usuario = usuarioDAO.recuperarPorEmail(email);
		return usuario;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<UsuarioTO> filtro) throws Exception {
		usuarioDAO.paginar(filtro);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Usuario> pesquisar(UsuarioTO usuarioTO) throws Exception{
		return usuarioDAO.pesquisar(usuarioTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirUsuario(Usuario usuario) throws Exception {
		usuarioDAO.excluirUsuario(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validarLogin(String login) throws Exception {
		if (usuarioDAO.isLoginCadastrado(login)) {
			throw new UsuarioException(OpaMensagemEnum.UC_USUARIO_LOGIN_JA_CADASTRADO.getKey());
		}
	}

}