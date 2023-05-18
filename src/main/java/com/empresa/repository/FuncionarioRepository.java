package com.empresa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empresa.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{	
	
	Page<Funcionario> findByNomeContainsIgnoreCase(String nome, Pageable paging);

	List<Funcionario> findByCargoId(Long id);
	
	Page<Funcionario> findByCargoId(Long id, Pageable paging);
	
	Page<Funcionario> findByDataEntrada(LocalDate dataEntrada, Pageable paging);
	
	Page<Funcionario> findByDataSaida(LocalDate dataSaida, Pageable paging);
	
	@Query(value = "SELECT f from Funcionario f where f.dataEntrada = :entrada and f.dataSaida = :saida")
	Page<Funcionario> findbyDataEntradaDataSaida(@Param("entrada") LocalDate dataEntrada, 
												@Param("saida") LocalDate dataSaida,
												Pageable paging);
	
	
	
}
