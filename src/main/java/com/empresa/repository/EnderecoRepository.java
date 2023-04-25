package com.empresa.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.domain.Endereco;

@Repository
@Transactional
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
