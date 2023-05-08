package com.empresa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_endereco")
public class Endereco extends AbstractEntity<Long> {
	
	@NotBlank(message = "Digite um logradouro")
	@Size(min = 3, max = 200, message = "Logradouro deve estar entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String logradouro;	
	
	@NotBlank(message = "Digite um bairro")
	@Size(min = 3, max = 200, message = "Bairro deve estar entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String bairro;
	
	@NotBlank(message = "Digite uma cidade")
	@Size(min = 3, max = 200, message = "Ciadde deve estar entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String cidade;
	
	@NotNull(message = "Escolha uma UF.")
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;
	
	@NotBlank(message = "Digite um CEP")
	@Size(min = 9, max = 9, message = "CEP deve conter {min} caracteres.")
	@Column(nullable = false, length = 9)
	private String cep;
	
	@NotNull(message = "Digite o numero da residencia.")
	@Column(nullable = false, length = 5)
	private Integer numero;
	private String complemento;
	
	public Endereco() {
		
	}

	public Endereco(String logradouro, String bairro, String cidade, UF uf, String cep, Integer numero,
			String complemento) {
		super();
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	

}
