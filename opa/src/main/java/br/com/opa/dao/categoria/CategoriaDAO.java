package br.com.opa.dao.categoria;

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
import br.com.opa.entity.Categoria;
import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.exception.categoria.CategoriaNaoEncontradoException;
import br.com.opa.infra.message.MensagemFactory;
import br.com.opa.to.CategoriaTO;

@Stateless
public class CategoriaDAO extends OpaDataAbstract<Categoria> {
	
	private @Inject MensagemFactory mensagemFactory;

	@Override
	public Categoria recuperar(Serializable id) throws Exception {
		CriteriaQuery<Categoria> criteria = getCriteriaBuilder().createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Categoria recuperarCompleto(Integer id) throws Exception {
		CriteriaQuery<Categoria> criteria = getCriteriaBuilder().createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		try {
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("id"), id))).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Categoria recuperarPorNome(String nome) throws CategoriaNaoEncontradoException {
		try {
			CriteriaQuery<Categoria> criteria = getCriteriaBuilder().createQuery(Categoria.class);
			Root<Categoria> root = criteria.from(Categoria.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("nome"), nome))).getSingleResult();
		} catch (NoResultException e) {
			throw new CategoriaNaoEncontradoException(mensagemFactory.getMensagem(OpaMensagemEnum.UC_CATEGORIA_NAO_ENCONTRADA.getKey())+": "+nome);
		}
	}

	/*public void paginar(FiltroTO<CategoriaTO> filtro) throws Exception {
		CriteriaQuery<Categoria> criteria = getCriteriaBuilder().createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		Predicate[] listaPredicate = comporFiltro(root, filtro.getObjeto(), criteria);
		Order ordenacao = comporOrdenacao(criteria, root, filtro.getCampoOrdenacao(), filtro.getOrdenacao());
		filtro.getObjeto().setCategorias(getManager().createQuery(criteria.multiselect(
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

	private Predicate[] comporFiltro(Root<Categoria> root, CategoriaTO categoriaTO, CriteriaQuery<?> criteria) {
		List<Predicate> listaPredicate = new ArrayList<>();
		if (!UtilNullEmpty.isNullOrEmpty(categoriaTO.getCategoria().getNome())) {
			listaPredicate.add(comporFiltroPorNome(categoriaTO.getCategoria().getNome(), root));
		}
		if (!UtilNullEmpty.isNullOrEmpty(categoriaTO.getCategoria().getId())) {
			listaPredicate.add(comporFiltroPorId(categoriaTO.getCategoria().getId(), root));
		}
		return (Predicate[]) listaPredicate.toArray(new Predicate[listaPredicate.size()]);
	}
	
	private Predicate comporFiltroPorNome(String nome, Root<Categoria> root) {
		return getCriteriaBuilder().like(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.<String>get("nome"))), "%"+nome.toLowerCase()+"%");
	}
	
	private Predicate comporFiltroPorId(Integer id, Root<Categoria> root) {
		return getCriteriaBuilder().equal(root.<String>get("id"), id);
	}

	/*private Integer realizarCount(CategoriaTO categoriaTO) {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Categoria> root = criteriaLong.from(Categoria.class);
		Predicate[] listaPredicate = comporFiltro(root, categoriaTO, criteriaLong);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(listaPredicate);
		return getManager().createQuery(criteriaLong).getSingleResult().intValue();
	}*/

	public List<Categoria> pesquisar(CategoriaTO categoriaTO) {
		CriteriaQuery<Categoria> criteria = getCriteriaBuilder().createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		Predicate[] listaPredicate = comporFiltro(root, categoriaTO, criteria);
		try {
			return getManager().createQuery(criteria.select(root).where(listaPredicate)).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void excluirCategoria(Categoria categoria) {
		CriteriaUpdate<Categoria> criteria = getCriteriaBuilder().createCriteriaUpdate(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		criteria.set("dataExclusao", new Date());
		criteria.where(getCriteriaBuilder().equal(root.get("id"),categoria.getId()));
		getManager().createQuery(criteria).executeUpdate();
	}

	public Boolean isCategoriaCadastrada(String nome) throws Exception {
		CriteriaQuery<Long> criteriaLong = getCriteriaBuilder().createQuery(Long.class);
		Root<Categoria> root = criteriaLong.from(Categoria.class);
		criteriaLong.select(getCriteriaBuilder().count(root)).where(
				getCriteriaBuilder().equal(getCriteriaBuilder().lower(getCriteriaBuilder().trim(root.get("nome"))), nome.toLowerCase()));
		return getManager().createQuery(criteriaLong).getSingleResult().intValue() > 0;
	}
	
}