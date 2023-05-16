package com.empresa;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.empresa.domain.Cargo;
import com.empresa.domain.Departamento;
import com.empresa.domain.Endereco;
import com.empresa.domain.UF;
import com.empresa.repository.CargoRepository;
import com.empresa.repository.DepartamentoRepository;
import com.empresa.service.EnderecoService;
import com.empresa.service.FuncionarioService;

@SpringBootApplication
public class EmpresaApplication implements CommandLineRunner{

	@Autowired 
	private CargoRepository cargoRepository;
	@Autowired 
	private FuncionarioService funcService;
	@Autowired 
	private DepartamentoRepository depRepository;
	@Autowired
	private EnderecoService endService;

	public static void main(String[] args) {
		SpringApplication.run(EmpresaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Departamento dep1 = new Departamento("Compras");
		Departamento dep2 = new Departamento("Tecnologia da Informação");
		Departamento dep3 = new Departamento("Transporte");
		Departamento dep4 = new Departamento("Comercial");
		Departamento dep5 = new Departamento("Produção");
		Departamento dep6 = new Departamento("Logistica");
		Departamento dep7 = new Departamento("Comunicação");
		Departamento dep8 = new Departamento("Marketing");
		Departamento dep9 = new Departamento("Recursos Humanos");
		Departamento dep10 = new Departamento("Estoque");
		Departamento dep11 = new Departamento("Contabilidade");
		Departamento dep12 = new Departamento("Juridico");
		Departamento dep13 = new Departamento("Qualidade");
		Departamento dep14 = new Departamento("Manutenção");
		Departamento dep15 = new Departamento("Secretaria");
		
		depRepository.saveAll(Arrays.asList(dep1, dep2, dep3, dep4, dep5, dep6, dep7, dep8, dep9, dep10, dep11, dep12, dep13, dep14, dep15));
		
		Cargo cargo1 = new Cargo("Assistente administrativo", dep2);
		Cargo cargo2 = new Cargo("Tecnico de informatica", dep2);
		Cargo cargo3 = new Cargo("Contador", dep4);
		Cargo cargo4 = new Cargo("Analista de sistemas", dep2);
		Cargo cargo5 = new Cargo("Programador Java Senior", dep2);
		Cargo cargo6 = new Cargo("Analista Pleno", dep10);
		Cargo cargo7 = new Cargo("Contador II", dep11);
		Cargo cargo8 = new Cargo("Analista de redes", dep3);
		Cargo cargo9 = new Cargo("Programador Java Pleno", dep2);
		Cargo cargo10 = new Cargo("Engenheiro de software", dep2);
		 
		cargoRepository.saveAll(Arrays.asList(cargo1, cargo2, cargo3, cargo4));
		cargoRepository.saveAll(Arrays.asList(cargo5, cargo6, cargo7, cargo8, cargo9, cargo10));
		
		Endereco end1 = new Endereco("Av Itavuvu", "Vila Carol", "Sorocaba", UF.SP, "145874525", 32, "apto 2");
		endService.salvar(end1);
		
		Endereco end2 = new Endereco("Av Ipanema", "Central Parque", "Sorocaba", UF.SP, "145874525", 13, "apto 2");
		endService.salvar(end2);
		
	
		
	}


}
