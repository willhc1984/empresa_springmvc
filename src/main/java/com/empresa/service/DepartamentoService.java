package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.domain.Departamento;
import com.empresa.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public Departamento salvar(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}

	public List<Departamento> buscarTodos() {
		return departamentoRepository.findAll();
	}

	public Optional<Departamento> buscarPorId(Long id) {
		return departamentoRepository.findById(id);
	}

	public Departamento editar(Departamento departamento) {
		return departamentoRepository.save(departamento);		
	}

	public boolean departamentoTemCargo(Long id) {
		if(buscarPorId(id).get().getCargos().isEmpty()) {
			return false;
		}
		return true;
	}

	public void excluir(Long id) {
		departamentoRepository.deleteById(id);
	}

}
