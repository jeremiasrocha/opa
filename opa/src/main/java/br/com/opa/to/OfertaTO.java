package br.com.opa.to;

import java.util.ArrayList;
import java.util.List;

import br.com.opa.entity.Categoria;
import br.com.opa.entity.Oferta;

public class OfertaTO {

    private Oferta oferta;
    private List<Oferta> ofertas;
    
    private CategoriaTO categoriaTO;
    private List<Categoria> categorias;

    private int idUsuario;
	private int idCategoria;
	private int idContratado;

	public Oferta getOferta() {
		if (oferta == null) {
			oferta = new Oferta();
		}
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public List<Oferta> getOfertas() {
		if (ofertas == null) {
			ofertas =  new ArrayList<>();
		}
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdContratado() {
		return idContratado;
	}

	public void setIdContratado(int idContratado) {
		this.idContratado = idContratado;
	}
	
	public List<Categoria> getCategorias() {
		if (categorias == null) {
			categorias =  new ArrayList<>();
		}
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public CategoriaTO getCategoriaTO() {
		return categoriaTO;
	}

	public void setCategoriaTO(CategoriaTO categoriaTO) {
		this.categoriaTO = categoriaTO;
	}

}