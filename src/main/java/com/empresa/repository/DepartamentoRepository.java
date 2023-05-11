package com.empresa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.empresa.domain.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>,
		PagingAndSortingRepository<Departamento, Long>{
	
	Page<Departamento> findAll(Pageable pageable);

	boolean existsByNome(String nome);
}
