package br.com.opa.service.oferta;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.opa.dao.OpaDataAbstract;
import br.com.opa.dao.oferta.OfertaDAO;
import br.com.opa.entity.Categoria;
import br.com.opa.entity.Oferta;
import br.com.opa.service.ServiceAbstract;
import br.com.opa.service.categoria.CategoriaService;
import br.com.opa.to.OfertaTO;
import br.com.opa.to.CategoriaTO;
import br.com.opa.to.FiltroTO;

@Stateless
public class OfertaService extends ServiceAbstract<Oferta> {

	private @Inject OfertaDAO ofertaDAO;
	private @Inject CategoriaService categoriaService;

	@Override
	protected OpaDataAbstract<Oferta> getEntityBean() {
		return ofertaDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Oferta recuperarCompletoPorNome(String nome) throws Exception {
		Oferta oferta = ofertaDAO.recuperarPorNome(nome);
		return oferta;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<OfertaTO> filtro) throws Exception {
		/*ofertaDAO.paginar(filtro);*/
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Oferta> pesquisar(OfertaTO ofertaTO) throws Exception{
		return ofertaDAO.pesquisar(ofertaTO);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Oferta> capturarOfertasUsuario(OfertaTO ofertaTO) throws Exception{
		return ofertaDAO.capturarOfertasUsuario(ofertaTO);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirOferta(Oferta oferta) throws Exception {
		ofertaDAO.excluirOferta(oferta);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Categoria> capturarCategorias(CategoriaTO categoriaTO) throws Exception{
		return categoriaService.pesquisar(categoriaTO);
	}
	
	
}