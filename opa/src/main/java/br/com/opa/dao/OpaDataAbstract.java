
package br.com.opa.dao;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.ConstraintViolationException;

import br.com.opa.util.UtilBeans;


/**
 * Classe responsavel por abstrair as implementacoes de CRUD basicas do sistema
 * @author Evandro Moura
 * @param <T>
 */
public abstract class OpaDataAbstract<T> extends DataAbstract {

	/**
	 * {@inheritDoc}
	 */
	public void alterar(T entity) throws Exception {
		this.getManager().merge(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void excluir(T entity) throws Exception {
		this.getManager().remove(this.getManager().find(this.getEntityType(),getIdValue(entity)));
//		this.getManager().remove(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public void incluir(T entity) throws Exception {
		try {
			this.getManager().persist(entity);

		} catch (ConstraintViolationException e) {
			throw e;
		} catch (Exception e) {
			if (e instanceof java.sql.BatchUpdateException) {
				((java.sql.BatchUpdateException) e).getNextException().printStackTrace();
				throw new RuntimeException(e);
			}else{
				e.printStackTrace();
			}	
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> listar() {
		Class entityType = this.getEntityType();
		Annotation[] annotations = entityType.getAnnotations();
		String namedQuery = null;
		for (Annotation annotation : annotations) {
			if (annotation.annotationType().equals(NamedQuery.class)) {
				NamedQuery named = (NamedQuery) annotation;
				namedQuery = named.name();
				break;
			}
		}
		Query query = null;
		if (namedQuery != null) {
			query = this.getManager().createNamedQuery(namedQuery);
		} else {
			query =
					this.getManager().createQuery(
							"FROM ".concat(entityType.getName()));
		}
		return query.getResultList();
	}
	
	
	public List<T> listarPaginado(int pagina, int totalPorPagina, int total)
			throws Exception {
		Class entityType = this.getEntityType();
		Query query = this.getManager()
				.createQuery("FROM ".concat(entityType.getName()))
				.setFirstResult(pagina * totalPorPagina)
				.setMaxResults(totalPorPagina);
		return query.getResultList();
	}

	/**
	 * Retorna o Tipo da Parametrizacao da concretizacao do Bean
	 * 
	 * @return clazz Class
	 */
	@SuppressWarnings("rawtypes")
	protected Class getEntityType() {
		ParameterizedType parameterizedType =
				(ParameterizedType) this.getClass().getGenericSuperclass();
		Class entityType =
				(Class) parameterizedType.getActualTypeArguments()[0];
		return entityType;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T recuperar(Serializable id) throws Exception{
		T r = (T) this.getManager().find(this.getEntityType(), id);
		if(r == null){
//			throw new BusinessException(ConstantesMensagem.NENHUM_REGISTRO_ENCONTRADO);
		}
		return r;
	}

	/**
	 * Retorna uma Lista de entidades de acordo com a query de pesquisa.
	 * 
	 * @param query
	 *            Query de pesquisa
	 * @param paramNames
	 *            Nomes dos parametros da pesquisa
	 * @param paramValues
	 *            Valores que cada parametro recebera
	 * @return list Lista de Entidades
	 */
	@SuppressWarnings("unchecked")
	protected List<T> pesquisarPorQuery(String query,
			String[] paramNames, Object[] paramValues) {
		Query queryMananger = this.getManager().createQuery(query);
		try {
			for (int i = 0; i < paramNames.length; i++) {
				queryMananger.setParameter(paramNames[i], paramValues[i]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					"Numero de parametros diferentes dos valores");
		}
		return queryMananger.getResultList();
	}

	/**
	 * Retorna uma Entidade de acordo com a query de pesquisa.
	 * 
	 * @param query
	 *            Query de pesquisa
	 * @param paramNames
	 *            Nomes dos parametros da pesquisa
	 * @param paramValues
	 *            Valores que cada parametro recebera
	 * @return list Lista de Entidades
	 * @throws Exception
	 *             e
	 */
	@SuppressWarnings("unchecked")
	protected T recuperarPorQuery(String query, String[] paramNames,
			Object[] paramValues) throws Exception {
		Query queryMananger = this.getManager().createQuery(query);
		T result = null;
		try {
			for (int i = 0; i < paramNames.length; i++) {
				queryMananger.setParameter(paramNames[i], paramValues[i]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					"Numero de parametros diferentes dos valores");
		}
		try {
			result = (T)queryMananger.getSingleResult();
		} catch (NoResultException e) {
//			this.logger.error("Nenhum registro encontrado para a entidade");
		}
		return result;
	}

	/**
	 * Retorna o id do objeto parametrizado
	 * 
	 * @param entity
	 *            Entidade que contem o id
	 * @return id Object
	 */
	private Map<String, Object> getId(Object entity) {
		Map<String, Object> mapa = new HashMap<String, Object>();

		try {
			Set<Field> fields = UtilBeans.getAllFields(entity.getClass());
			GOTO: for (Field field : fields) {
				Annotation[] annotationsFields = field.getAnnotations();
				for (Annotation annotation : annotationsFields) {
					if (annotation.annotationType().equals(Id.class)
							|| annotation.annotationType().equals(
									EmbeddedId.class)) {
						field.setAccessible(true);
						mapa.put(field.getName(), field.get(entity));
						break GOTO;
					}
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Nao foi possivel encontrar o Id da Entidade "
							.concat(entity.getClass().getName()));
		}

		return mapa;
	}

	/**
	 * Retorna o Nome da Propriedade que representa a chave primaria da Entidade
	 * 
	 * @return string Nome do campo Id
	 */
	private String getIdName(T entity) {
		final Map<String, Object> id = this.getId(entity);
		return id.keySet().iterator().next();
	}

	private Object getIdValue(T entity) {
		return this.getId(entity).get(this.getIdName(entity));
	}

	/**
	 * {@inheritDoc}
	 */
	public void excluirLista(Collection<T> list) throws Exception {
		if ((list == null) || (list.size() < 1)) {
			return;
		}
		StringBuilder jql = new StringBuilder("DELETE FROM ");
		jql.append(this.getEntityType().getName());
		jql.append(" entity ");
		jql.append(" WHERE ");
		jql.append(" entity ");
		// jql.append(getIdName(lista.iterator().next()));
		jql.append(" IN (:list) ");
		Query query = this.getManager().createQuery(jql.toString());
		query.setParameter("list", list);
		query.executeUpdate();
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void incluirLista(Collection<T> list) throws Exception {
		try{
			if ((list == null) || (list.size() < 1)) {
				return;
			}
			for (T entity : list) {
				this.incluir(entity);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public void alterarLista(Collection<T> list) throws Exception {
		if ((list == null) || (list.size() < 1)) {
			return;
		}
		for (T entity : list) {
			this.alterar(entity);
		}
	}
	
	public T get(T entity){
		
		return null;
	}

}