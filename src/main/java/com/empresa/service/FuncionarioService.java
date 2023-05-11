package com.empresa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Funcionario> buscarTodos(Pageable pageable) {		
		return funcRepository.findAll(pageable);
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
	
	public List<Funcionario> buscartPorNome(String nome){
		return funcRepository.findByNomeContainsIgnoreCase(nome);
	}

	public List<Funcionario> buscarPorCargo(Long id) {
		return funcRepository.findByCargoId(id);
	}

	public List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida) {
		
		if(entrada != null && saida != null) {
			return funcRepository.findbyDataEntradaDataSaida(entrada, saida);
		}
		
		if(entrada != null) {
			return funcRepository.findByDataEntrada(entrada);
		}		
		if(saida != null) {
			return funcRepository.findByDataSaida(saida);
			
		}
		
		return new ArrayList<>();
		
	}

}
