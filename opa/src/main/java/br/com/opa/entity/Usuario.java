package br.com.opa.entity;
// default package
// Generated 09/05/2017 16:05:30 by Hibernate Tools 4.3.5.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.opa.enums.StatusUsuarioEnum;

/**
 * Usuario generated by hbm2java
 */

//@Entity
//@Table(name = "usuario", schema = "public")
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = -6224361341258353200L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "login", nullable = false, length = 50)
	private String login;
	
	@Column(name = "perfil", nullable = false, length = 1)
	private String perfil;
	
	@Column(name = "senha", nullable = false, length = 100)
	private String senha;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "telefone", length = 15)
	private String telefone;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_exclusao")
	private Date dataExclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inativacao")
	private Date dataInativacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_alteracao")
	private Date dataAlteracao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 1)
	private StatusUsuarioEnum status;
	
	@Transient
	private boolean acessoPublico;

	public Usuario() {
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	
	public StatusUsuarioEnum getStatus() {
		return status;
	}

	public void setStatus(StatusUsuarioEnum status) {
		this.status = status;
	}
	
	public boolean isAcessoPublico() {
		return acessoPublico;
	}

	public void setAcessoPublico(boolean acessoPublico) {
		this.acessoPublico = acessoPublico;
	}

}
