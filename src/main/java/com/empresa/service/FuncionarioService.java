package com.empresa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.domain.Funcionario;
import com.empresa.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcRepository;
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		return funcRepository.save(funcionario);
	}
	
	public List<Funcionario> buscarTodos(){
		return funcRepository.findAll();
	}
	
	public void excluir(Long id) {
		funcRepository.deleteById(id);
	}

	public Funcionario buscarPorId(Long id) {
		return funcRepository.findById(id).get();
	}

	public Funcionario editar(Funcionario funcionario) {
		return funcRepository.save(funcionario);		
	}

}
