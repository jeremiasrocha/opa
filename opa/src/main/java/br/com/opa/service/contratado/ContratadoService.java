package br.com.opa.service.contratado;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.opa.dao.OpaDataAbstract;
import br.com.opa.dao.contratado.ContratadoDAO;
import br.com.opa.entity.Contratado;
import br.com.opa.service.ServiceAbstract;
import br.com.opa.to.ContratadoTO;
import br.com.opa.to.FiltroTO;

@Stateless
public class ContratadoService extends ServiceAbstract<Contratado> {

	private @Inject ContratadoDAO contratadoDAO;

	@Override
	protected OpaDataAbstract<Contratado> getEntityBean() {
		return contratadoDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Contratado recuperarCompletoPorNome(String nome) throws Exception {
		Contratado contratado = contratadoDAO.recuperarPorNome(nome);
		return contratado;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void paginar(FiltroTO<ContratadoTO> filtro) throws Exception {
		/*contratadoDAO.paginar(filtro);*/
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Contratado> pesquisar(ContratadoTO contratadoTO) throws Exception{
		return contratadoDAO.pesquisar(contratadoTO);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirContratado(Contratado contratado) throws Exception {
		contratadoDAO.excluirContratado(contratado);
	}
	
}