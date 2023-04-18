package com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.domain.Cargo;
import com.empresa.repository.CargoRepository;

@Service
public class CargoService {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	public Cargo salvar(Cargo cargo) {
		return cargoRepository.save(cargo);
	}
	
	public List<Cargo> buscarTodos(){
		return cargoRepository.findAll();
	}
	
	public void excluir(Long id) {
		cargoRepository.deleteById(id);
	}

}
