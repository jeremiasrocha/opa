package br.com.opa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.opa.enums.StatusContratadoEnum;
import br.com.opa.enums.TipoContratadoEnum;

/**
 * Contratado generated by hbm2java
 */

@Entity
@Table(name = "opa_contratado")
public class Contratado implements java.io.Serializable {

	private static final long serialVersionUID = 2813490954572828459L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name="SEQ_CONTRATADO", sequenceName="OPA_SEQ_CONTRATADO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CONTRATADO")
	private int id;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "codigo", nullable = false)
	private int codigo;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@Column(name = "telefone")
	private int telefone;
	
	@Column(name = "endereco", length = 200)
	private String endereco;
	
	@Column(name = "qtd_ticket_gerado")
	private int qtdTicketGerado;
	
	@Column(name = "qtd_ticket_utilizado")
	private int qtdTicketUtilizado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 50)
	private StatusContratadoEnum status;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false, length = 50)
	private TipoContratadoEnum tipo;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_exclusao")
	private Date dataExclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inativacao")
	private Date dataInativacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_alteracao")
	private Date dataAlteracao;
		
	@Transient
	private boolean acessoPublico;

	public Contratado() {
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Date getDataInativacao() {
		return dataInativacao;
	}

	public void setDataInativacao(Date dataInativacao) {
		this.dataInativacao = dataInativacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public StatusContratadoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusContratadoEnum status) {
		this.status = status;
	}
	
	public TipoContratadoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoContratadoEnum tipo) {
		this.tipo = tipo;
	}
	
	public boolean isAcessoPublico() {
		return acessoPublico;
	}

	public void setAcessoPublico(boolean acessoPublico) {
		this.acessoPublico = acessoPublico;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getQtdTicketGerado() {
		return qtdTicketGerado;
	}

	public void setQtdTicketGerado(int qtdTicketGerado) {
		this.qtdTicketGerado = qtdTicketGerado;
	}

	public int getQtdTicketUtilizado() {
		return qtdTicketUtilizado;
	}

	public void setQtdTicketUtilizado(int qtdTicketUtilizado) {
		this.qtdTicketUtilizado = qtdTicketUtilizado;
	}

}
