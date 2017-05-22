package br.com.opa.service.security;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.opa.entity.Usuario;

@Named
public class SecurityBean {
	
	/*private @Inject FuncionalidadeService funcionalidadeService;
	
	public List<Funcionalidade> getMenuUsuario(Usuario usuario)throws Exception{
		List<Funcionalidade> funcionalidadesPais = funcionalidadeService.pesquisarPorUsuario(usuario);
		comporFilhosFuncionalidade(usuario, funcionalidadesPais);	
		return funcionalidadesPais;
	}

	private void comporFilhosFuncionalidade(Usuario usuario, List<Funcionalidade> funcionalidadesPais) {
		for (Funcionalidade funcionalidade : funcionalidadesPais) {
			funcionalidade.setFuncionalidadeFilhos(comporFilhos(capturarFilhos(usuario, funcionalidade),usuario));
			comporFilhosFuncionalidade(usuario, funcionalidade.getFuncionalidadeFilhos());
		}
	}

	private List<Funcionalidade> capturarFilhos(Usuario usuario, Funcionalidade funcionalidade) {
		return funcionalidadeService.recuperarFilhos(funcionalidade, usuario);
	}

	private List<Funcionalidade> comporFilhos(List<Funcionalidade> recuperarFilhos, Usuario usuario) {
		comporFilhosFuncionalidade(usuario, recuperarFilhos);
		return recuperarFilhos;
	}*/
	

}
