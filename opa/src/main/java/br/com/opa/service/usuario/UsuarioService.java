package br.com.opa.service.usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.opa.exception.usuario.UsuarioException;
import br.com.opa.dao.OpaDataAbstract;
import br.com.opa.dao.usuario.UsuarioDAO;
import br.com.opa.entity.Usuario;
import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.enums.StatusUsuarioEnum;
import br.com.opa.service.ServiceAbstract;
import br.com.opa.to.FiltroTO;
import br.com.opa.to.UsuarioTO;
import br.com.opa.util.UtilString;

@Stateless
public class UsuarioService extends ServiceAbstract<Usuario> {

	private @Inject UsuarioDAO usuarioDAO;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(Usuario usuario) throws Exception {
		prepararParaIncluir(usuario);
		usuarioDAO.incluir(usuario);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(Usuario usuario) throws Exception {
		prepararParaAlterar(usuario);
		usuarioDAO.alterar(usuario);
	}

	private void prepararParaAlterar(Usuario usuario) {
		usuario.setDataAlteracao(new Date());
	}

	private void prepararParaIncluir(Usuario usuario) {
		usuario.setDataCadastro(new Date());
		usuario.setStatus(StatusUsuarioEnum.A);
	}

	@Override
	protected OpaDataAbstract<Usuario> getEntityBean() {
		return usuarioDAO;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario recuperarCompleto(Integer id) throws Exception {
		Usuario usuario = usuarioDAO.recuperarCompleto(id);
		recuperarParametros(usuario);
		return usuario;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public Usuario recuperar(Serializable id) throws Exception {
		Usuario usuario = usuarioDAO.recuperar(id);
		return usuario;
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