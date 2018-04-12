package br.com.opa.service.categoria;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.opa.dao.OpaDataAbstract;
import br.com.opa.dao.categoria.CategoriaDAO;
import br.com.opa.entity.Categoria;
import br.com.opa.service.ServiceAbstract;
import br.com.opa.to.CategoriaTO;
import br.com.opa.to.FiltroTO;

@Stateless
public class CategoriaService extends ServiceAbstract<Categoria> {

	private @Inject CategoriaDAO categoriaDAO;

	@Override
	protected OpaDataAbstract<Categoria> getEntityBean() {
		return categoriaDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Categoria recuperarCompletoPorNome(String nome) throws Exception {
		Categoria categoria = categoriaDAO.recuperarPorNome(nome);
		return categoria;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<CategoriaTO> filtro) throws Exception {
		/*categoriaDAO.paginar(filtro);*/
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Categoria> pesquisar(CategoriaTO categoriaTO) throws Exception{
		return categoriaDAO.pesquisar(categoriaTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirCategoria(Categoria categoria) throws Exception {
		categoriaDAO.excluirCategoria(categoria);
	}
	
}