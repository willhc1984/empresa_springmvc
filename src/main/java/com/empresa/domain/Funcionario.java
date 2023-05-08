package com.empresa.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_funcionario")
public class Funcionario extends AbstractEntity<Long>{
	
	@Size(min = 3, max = 200)
	@NotBlank(message = "Digite um nome.")
	@Column(nullable = false, unique = true)
	private String nome;
	
	@NotNull(message = "Digite um salário.")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false)
	private BigDecimal salario;
	
	@NotNull(message = "Defina uma data de entrada")
	@PastOrPresent(message = "Data de entrada inválida")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_entrada", nullable = false)
	private LocalDate dataEntrada;
	
	@PastOrPresent(message = "Data de saida inválida")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_saida")
	private LocalDate dataSaida;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
	
	@NotNull(message = "Escolha um cargo.")
	@ManyToOne
	@JoinColumn(name = "cargo_id_fk")
	private Cargo cargo;
	
	public Funcionario() {
		
	}

	public String getNome() {
		return nome;
	}

	public Funcionario(String nome, BigDecimal salario, LocalDate dataEntrada, LocalDate dataSaida, Endereco endereco,
			Cargo cargo) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.endereco = endereco;
		this.cargo = cargo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	
}
