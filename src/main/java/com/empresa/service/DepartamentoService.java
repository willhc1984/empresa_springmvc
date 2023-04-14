package com.empresa.service;

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

}
