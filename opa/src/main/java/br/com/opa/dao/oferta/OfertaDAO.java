package br.com.opa.dao.oferta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.opa.dao.OpaDataAbstract;
import br.com.opa.util.UtilNullEmpty;
import br.com.opa.entity.Oferta;
import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.enums.StatusOfertaEnum;
import br.com.opa.exception.oferta.OfertaNaoEncontradoException;
import br.com.opa.infra.message.MensagemFactory;
import br.com.opa.to.OfertaTO;

@Stateless
public class OfertaDAO extends OpaDataAbstract<Oferta> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Oferta recuperar(Serializable id) throws Exception {
		CriteriaQuery<Oferta> criteria = getCriteriaBuilder().createQuery(Oferta.class);
		Root<Oferta> root = criteria.from(Oferta.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Oferta recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Oferta> criteria = getCriteriaBuilder().createQuery(Oferta.class);
		Root<Oferta> root = criteria.from(Oferta.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Oferta recuperarPorNome(String nome) throws OfertaNaoEncontradoException {
		try {
			CriteriaQuery<Oferta> criteria = getCriteriaBuilder().createQuery(Oferta.class);
			Root<Oferta> root = criteria.from(Oferta.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new OfertaNaoEncontradoException(mensagemFactory.getMensagem(OpaMensagemEnum.UC_OFERTA_NAO_ENCONTRADA.getKey())+": "+nome);
		}
	}

	/*public void paginar(FiltroTO<OfertaTO> filtro) throws Exception {
		CriteriaQuery<Oferta> criteria = getCriteriaBuilder().createQuery(Oferta.class);
		Root<Oferta> root = criteria.from(Oferta.class);
		Predicate[] listaPredicate = comporFiltro(root, filtro.getObjeto(), criteria);
		Order ordenacao = comporOrdenacao(criteria, root, filtro.getCampoOrdenacao(), filtro.getOrdenacao());
		filtro.getObjeto().setOfertas(getManager().createQuery(criteria.multiselect(
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

	private Predicate[] comporFiltro(Root<Oferta> root, OfertaTO ofertaTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		if (!UtilNullEmpty.isNullOrEmpty(ofertaTO.getOferta().getNome())) {
			listaPredicate.add(comporFiltroPorNome(ofertaTO.getOferta().getNome(), root));
		}
		if (ofertaTO.getOferta().getIdUsuario() != 0) {
			listaPredicate.add(comporFiltroPorIdUsuario(ofertaTO.getOferta().getIdUsuario(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(ofertaTO.getOferta().getId())) {
			listaPredicate.add(comporFiltroPorId(ofertaTO.getOferta().getId(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorNome(String nome, Root<Oferta> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("nome"))), "%"+nome.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorIdUsuario(int idUsuario, Root<Oferta> root) {
		return getCriteriaBuilder().equal(root.<String>get("idUsuario"), idUsuario);
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Oferta> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}
	
	/*private Order comporOrdenacao(CriteriaQuery<Oferta> criteria, Root<Oferta> root, String campoOrdenacao, String ordenacao) {
		Expression<Oferta> expression = null;
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
	}*/

	/*private Integer realizarCount(OfertaTO ofertaTO) {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Oferta> root = criteriaLong.from(Oferta.class);
		Predicate[] listaPredicate = comporFiltro(root, ofertaTO, criteriaLong);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(listaPredicate);
		return getManager().createQuery(criteriaLong).getSingleResult().intValue();
	}*/

	public List<Oferta> pesquisar(OfertaTO ofertaTO) {
		CriteriaQuery<Oferta> criteria = getCriteriaBuilder().createQuery(Oferta.class);
		Root<Oferta> root = criteria.from(Oferta.class);
		Predicate[] listaPredicate = comporFiltro(root, ofertaTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Oferta> capturarOfertasUsuario(OfertaTO ofertaTO) {
		CriteriaQuery<Oferta> criteria = getCriteriaBuilder().createQuery(Oferta.class);
		Root<Oferta> root = criteria.from(Oferta.class);
		Predicate[] listaPredicate = comporFiltro(root, ofertaTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirOferta(Oferta oferta) {
		CriteriaUpdate<Oferta> criteria = getCriteriaBuilder().createCriteriaUpdate(Oferta.class);
		Root<Oferta> root = criteria.from(Oferta.class);
		criteria.set("situacao", StatusOfertaEnum.E);
		criteria.set("dataExclusao", new Date());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),oferta.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}

	public Boolean isOfertaCadastradoPorNome(String nome) throws Exception {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Oferta> root = criteriaLong.from(Oferta.class);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(
				getCriteriaBuilder().equal(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.get("nome"))), nome.toLowerCase()));
		return getManager().createQuery(criteriaLong).getSingleResult().intValue() > 0;
	}
	
}