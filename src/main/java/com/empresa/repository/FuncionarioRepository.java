package com.empresa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empresa.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{	
	
	List<Funcionario> findByNomeContainsIgnoreCase(String nome);

	List<Funcionario> findByCargoId(Long id);
	
	List<Funcionario> findByDataEntrada(LocalDate dataEntrada);
	
	List<Funcionario> findByDataSaida(LocalDate dataSaida);
	
	@Query(value = "SELECT f from Funcionario f where f.dataEntrada = :entrada and f.dataSaida = :saida")
	List<Funcionario> findbyDataEntradaDataSaida(@Param("entrada") LocalDate dataEntrada, 
												@Param("saida") LocalDate dataSaida);
	
	
	
}
