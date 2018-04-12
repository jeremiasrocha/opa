package br.com.opa.dao.contratado;

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
import br.com.opa.entity.Contratado;
import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.enums.StatusContratadoEnum;
import br.com.opa.exception.contratado.ContratadoNaoEncontradoException;
import br.com.opa.infra.message.MensagemFactory;
import br.com.opa.to.ContratadoTO;
import br.com.opa.to.FiltroTO;

@Stateless
public class ContratadoDAO extends OpaDataAbstract<Contratado> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Contratado recuperar(Serializable id) throws Exception {
		CriteriaQuery<Contratado> criteria = getCriteriaBuilder().createQuery(Contratado.class);
		Root<Contratado> root = criteria.from(Contratado.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Contratado recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Contratado> criteria = getCriteriaBuilder().createQuery(Contratado.class);
		Root<Contratado> root = criteria.from(Contratado.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Contratado recuperarPorNome(String nome) throws ContratadoNaoEncontradoException {
		try {
			CriteriaQuery<Contratado> criteria = getCriteriaBuilder().createQuery(Contratado.class);
			Root<Contratado> root = criteria.from(Contratado.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new ContratadoNaoEncontradoException(mensagemFactory.getMensagem(OpaMensagemEnum.UC_CONTRATADO_NAO_ENCONTRADO.getKey())+": "+nome);
		}
	}

	/*public void paginar(FiltroTO<ContratadoTO> filtro) throws Exception {
		CriteriaQuery<Contratado> criteria = getCriteriaBuilder().createQuery(Contratado.class);
		Root<Contratado> root = criteria.from(Contratado.class);
		Predicate[] listaPredicate = comporFiltro(root, filtro.getObjeto(), criteria);
		Order ordenacao = comporOrdenacao(criteria, root, filtro.getCampoOrdenacao(), filtro.getOrdenacao());
		filtro.getObjeto().setContratados(getManager().createQuery(criteria.multiselect(
				root.get("id"),
				root.get("nome"),
				root.get("login"),
				root.get("operadora").get("codigo"),
				root.get("situacao")
				).where(
				listaPredicate).orderBy(ordenacao)).setFirstResult(
						filtro.getInicio()).setMaxResults(filtro.getTamanho()).getResultList());
		filtro.setTotalRegistros(realizarCount(filtro.getObjeto()));
	}*/

	private Predicate[] comporFiltro(Root<Contratado> root, ContratadoTO contratadoTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		if (!UtilNullEmpty.isNullOrEmpty(contratadoTO.getContratado().getNome())) {
			listaPredicate.add(comporFiltroPorNome(contratadoTO.getContratado().getNome(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(contratadoTO.getContratado().getId())) {
			listaPredicate.add(comporFiltroPorId(contratadoTO.getContratado().getId(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(contratadoTO.getContratado().getEmail())) {
			listaPredicate.add(comporFiltroPorEmail(contratadoTO.getContratado().getEmail(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorNome(String nome, Root<Contratado> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("nome"))), "%"+nome.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Contratado> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	private Predicate comporFiltroPorEmail(String email, Root<Contratado> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("email"))), "%"+email.toLowerCase()+"%");
	}
	
	private Order comporOrdenacao(CriteriaQuery<Contratado> criteria, Root<Contratado> root, String campoOrdenacao, String ordenacao) {
		Expression<Contratado> expression = null;
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

	private Integer realizarCount(ContratadoTO contratadoTO) {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Contratado> root = criteriaLong.from(Contratado.class);
		Predicate[] listaPredicate = comporFiltro(root, contratadoTO, criteriaLong);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(listaPredicate);
		return getManager().createQuery(criteriaLong).getSingleResult().intValue();
	}

	public List<Contratado> pesquisar(ContratadoTO contratadoTO) {
		CriteriaQuery<Contratado> criteria = getCriteriaBuilder().createQuery(Contratado.class);
		Root<Contratado> root = criteria.from(Contratado.class);
		Predicate[] listaPredicate = comporFiltro(root, contratadoTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirContratado(Contratado contratado) {
		CriteriaUpdate<Contratado> criteria = getCriteriaBuilder().createCriteriaUpdate(Contratado.class);
		Root<Contratado> root = criteria.from(Contratado.class);
		criteria.set("situacao", StatusContratadoEnum.E);
		criteria.set("dataExclusao", new Date());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),contratado.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}

	public Boolean isContratadoCadastradoPorNome(String nome) throws Exception {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Contratado> root = criteriaLong.from(Contratado.class);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(
				getCriteriaBuilder().equal(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.get("nome"))), nome.toLowerCase()));
		return getManager().createQuery(criteriaLong).getSingleResult().intValue() > 0;
	}
	
}