package br.com.opa.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.opa.dao.OpaDataAbstract;

public abstract class ServiceAbstract<T> {

	protected abstract OpaDataAbstract<T> getEntityBean();

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(T entity)throws Exception{
		getEntityBean().incluir(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(T entity)throws Exception{
		getEntityBean().alterar(entity);
	}

	public void incluirLista(Collection<T> lista) throws Exception {
		getEntityBean().incluirLista(lista);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> listar()throws Exception{
		return getEntityBean().listar();
	}

	public void excluir(T entity)throws Exception{
		getEntityBean().excluir(entity);
	}
	
	public T get(T entity){
		return null;
	}
	
	public T recuperar(Serializable id)throws Exception{
		return getEntityBean().recuperar(id);
	}

}