package com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Cargo> buscarTodos(Pageable pageable){
		return cargoRepository.findAll(pageable);
	}
	
	public List<Cargo> buscarTodos(){
		return cargoRepository.findAll();
	}
	
	public void excluir(Long id) {
		cargoRepository.deleteById(id);
	}

	public Cargo buscarPorId(Long id) {
		return cargoRepository.findById(id).get();
	}

	public Cargo editar(Cargo cargo) {
		return cargoRepository.save(cargo);		
	}
	
	public boolean cargoTemFuncionario(Long id) {
		if(buscarPorId(id).getFuncionarios().isEmpty()){
			return false;
		}
		return true;
	}
	

}
