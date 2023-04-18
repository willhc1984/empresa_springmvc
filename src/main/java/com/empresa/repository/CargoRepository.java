package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.domain.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{
	
	

}
