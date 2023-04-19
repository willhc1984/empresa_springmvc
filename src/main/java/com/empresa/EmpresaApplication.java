package com.empresa;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.empresa.domain.Cargo;
import com.empresa.domain.Departamento;
import com.empresa.repository.CargoRepository;
import com.empresa.repository.DepartamentoRepository;
import com.empresa.repository.FuncionarioRepository;

@SpringBootApplication
public class EmpresaApplication implements CommandLineRunner{
	
	@Autowired 
	private CargoRepository cargoRepository;
	@Autowired 
	private FuncionarioRepository funcRepository;
	@Autowired 
	private DepartamentoRepository depRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmpresaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Departamento dep1 = new Departamento("Tecnologia da Informação");
		Departamento dep2 = new Departamento("Secretaria");
		Departamento dep3 = new Departamento("Recursos Humanos");
		Departamento dep4 = new Departamento("Contabilidade");
		
		depRepository.saveAll(Arrays.asList(dep1, dep2, dep3, dep4));
		
		Cargo cargo1 = new Cargo("Assistente administrativo", dep2);
		Cargo cargo2 = new Cargo("Tecnico de informatica", dep1);
		Cargo cargo3 = new Cargo("Contador", dep4);
		Cargo cargo4 = new Cargo("Analista administrativo", dep3);
		 
		cargoRepository.saveAll(Arrays.asList(cargo1, cargo2, cargo3, cargo4));
		
	}

}
