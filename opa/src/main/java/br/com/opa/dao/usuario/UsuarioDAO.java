package br.com.opa.dao.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.opa.dao.OpaDataAbstract;
import br.com.opa.util.UtilNullEmpty;
import br.com.opa.util.UtilString;
import br.com.opa.entity.Usuario;
import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.enums.StatusUsuarioEnum;
import br.com.opa.exception.usuario.UsuarioNaoEncontradoException;
import br.com.opa.infra.message.MensagemFactory;
import br.com.opa.to.FiltroTO;
import br.com.opa.to.UsuarioTO;

@Stateless
public class UsuarioDAO extends OpaDataAbstract<Usuario> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Usuario recuperar(Serializable id) throws Exception {
		CriteriaQuery<Usuario> criteria = getCriteriaBuilder().createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Usuario> criteria = getCriteriaBuilder().createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario login(String userId, String password)throws Exception {
		try {
			CriteriaQuery<Usuario> criteria = getCriteriaBuilder().createQuery(Usuario.class);
			Root<Usuario> root = criteria.from(Usuario.class);
			return getManager().createQuery(
					criteria.select(root)
							.where(getCriteriaBuilder().equal(root.get("email"),
									userId),getCriteriaBuilder().equal(root.get("senha"),
											password))).getSingleResult();
		} catch (NoResultException e) {
			throw new UsuarioNaoEncontradoException();
		}
	}

	public Usuario recuperarPorLogin(String login) throws UsuarioNaoEncontradoException {
		try {
			CriteriaQuery<Usuario> criteria = getCriteriaBuilder().createQuery(Usuario.class);
			Root<Usuario> root = criteria.from(Usuario.class);
			root.fetch("usuarioPerfis",JoinType.LEFT);
			Usuario usuario = getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("login"),login))).getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			throw new UsuarioNaoEncontradoException(mensagemFactory.getMensagem(OpaMensagemEnum.UC_USUARIO_NAO_ENCONTRADO.getKey())+": "+login);
		}
	}
	
	public Usuario recuperarPorEmail(String email){
		CriteriaQuery<Usuario> criteria = getCriteriaBuilder().createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		root.fetch("operadora",JoinType.LEFT);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("email"),email))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void paginar(FiltroTO<UsuarioTO> filtro) throws Exception {
		CriteriaQuery<Usuario> criteria = getCriteriaBuilder().createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		Predicate[] listaPredicate = comporFiltro(root, filtro.getObjeto(), criteria);
		Order ordenacao = comporOrdenacao(criteria, root, filtro.getCampoOrdenacao(), filtro.getOrdenacao());
		filtro.getObjeto().setUsuarios(getManager().createQuery(criteria.multiselect(
				root.get("id"),
				root.get("nome"),
				root.get("login"),
				root.get("operadora").get("codigo"),
				root.get("situacao")
				).where(
				listaPredicate).orderBy(ordenacao)).setFirstResult(
						filtro.getInicio()).setMaxResults(filtro.getTamanho()).getResultList());
		filtro.setTotalRegistros(realizarCount(filtro.getObjeto()));
	}

	private Predicate[] comporFiltro(Root<Usuario> root, UsuarioTO usuarioTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		if (!UtilNullEmpty.isNullOrEmpty(usuarioTO.getUsuario().getStatus())) {
			listaPredicate.add(comporFiltroPorSituacao(usuarioTO.getUsuario().getStatus(),root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(usuarioTO.getUsuario().getNome())) {
			listaPredicate.add(comporFiltroPorNome(usuarioTO.getUsuario().getNome(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(usuarioTO.getUsuario().getId())) {
			listaPredicate.add(comporFiltroPorId(usuarioTO.getUsuario().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(usuarioTO.getUsuario().getEmail())) {
			listaPredicate.add(comporFiltroPorEmail(usuarioTO.getUsuario().getEmail(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorNome(String nome, Root<Usuario> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("nome"))), "%"+nome.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Usuario> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorSituacao(StatusUsuarioEnum status, Root<Usuario> root) {
		return getCriteriaBuilder().equal(root.<String>get("status"), status);
	}
	
	private Predicate comporFiltroPorEmail(String email, Root<Usuario> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("email"))), "%"+email.toLowerCase()+"%");
	}
	
	private Order comporOrdenacao(CriteriaQuery<Usuario> criteria, Root<Usuario> root, String campoOrdenacao, String ordenacao) {
		Expression<Usuario> expression = null;
		if (!new UtilString().vazio(campoOrdenacao)) {
			expression = root.get(campoOrdenacao);
		} else {
			expression = root.get("id");
		}
		if (ordenacao.equals("desc")) {
			return getCriteriaBuilder().desc(expression);
		} else {
			return getCriteriaBuilder().asc(expression);
		}
	}

	private Integer realizarCount(UsuarioTO usuarioTO) {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Usuario> root = criteriaLong.from(Usuario.class);
		Predicate[] listaPredicate = comporFiltro(root, usuarioTO, criteriaLong);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(listaPredicate);
		return getManager().createQuery(criteriaLong).getSingleResult().intValue();
	}

	public List<Usuario> pesquisar(UsuarioTO usuarioTO) {
		CriteriaQuery<Usuario> criteria = getCriteriaBuilder().createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		Predicate[] listaPredicate = comporFiltro(root, usuarioTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirUsuario(Usuario usuario) {
		CriteriaUpdate<Usuario> criteria = getCriteriaBuilder().createCriteriaUpdate(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		criteria.set("situacao", StatusUsuarioEnum.E);
		criteria.set("dataExclusao", new Date());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),usuario.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}

	public Boolean isLoginCadastrado(String login) throws Exception {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Usuario> root = criteriaLong.from(Usuario.class);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(
				getCriteriaBuilder().equal(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.get("login"))), login.toLowerCase()));
		return getManager().createQuery(criteriaLong).getSingleResult().intValue() > 0;
	}
	
}