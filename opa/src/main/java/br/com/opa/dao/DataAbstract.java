package br.com.opa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

public abstract class DataAbstract {

	@PersistenceContext(unitName="primary")
	private EntityManager manager;

	/**
	 * Retorna o(a) manager.
	 * 
	 * @return EntityManager
	 */
	protected EntityManager getManager() {
		return this.manager;
	}
	/**
	 * Retorna o(a) Criteria Builder.
	 * 
	 * @return EntityManager
	 */
	protected CriteriaBuilder getCriteriaBuilder() {
		return this.manager.getCriteriaBuilder();
	}

	/**
	 * Atribui o(a) manager.
	 * 
	 * @param manager
	 *            EntityManager
	 */
	protected final void setManager(EntityManager manager) {
		this.manager = manager;
	}

//	protected Session getSession() {
//		return getManager().unwrap(SessionImpl.class);
//	}

	//Usar somente se necessario verificar relatorioDAO doWork
//	protected Connection getConnection() throws SQLException {
//		return ((SessionImpl)getSession()).connection();
//	}

}