package com.empresa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.domain.Endereco;
import com.empresa.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository endRepository;
	
	@Transactional
	public Endereco salvar(Endereco endereco) {
		return endRepository.save(endereco);
	}

	public List<Endereco> buscarTodos() {
		return endRepository.findAll();
	}

	public Optional<Endereco> buscarPorId(Long id) {
		return endRepository.findById(id);
	}

	public Endereco editar(Endereco endereco) {
		return endRepository.save(endereco);		
	}

	public void excluir(Long id) {
		endRepository.deleteById(id);
	}

}
