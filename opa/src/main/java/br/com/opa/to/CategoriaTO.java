package br.com.opa.to;

import java.util.ArrayList;
import java.util.List;

import br.com.opa.entity.Categoria;

public class CategoriaTO {

    private Categoria categoria;
    private List<Categoria> categorias;

	public Categoria getCategoria() {
		if (categoria == null) {
			categoria = new Categoria();
		}
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

}