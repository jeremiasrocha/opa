package br.com.opa.to;

import java.util.ArrayList;
import java.util.List;

import br.com.opa.entity.Contratado;

public class ContratadoTO {

    private Contratado contratado;
    private List<Contratado> contratados;

	public Contratado getContratado() {
		if (contratado == null) {
			contratado = new Contratado();
		}
		return contratado;
	}

	public void setContratado(Contratado contratado) {
		this.contratado = contratado;
	}

	public List<Contratado> getContratados() {
		if (contratados == null) {
			contratados =  new ArrayList<>();
		}
		return contratados;
	}

	public void setContratados(List<Contratado> contratados) {
		this.contratados = contratados;
	}

}