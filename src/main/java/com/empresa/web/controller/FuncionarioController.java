package com.empresa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.domain.Funcionario;
import com.empresa.service.CargoService;
import com.empresa.service.FuncionarioService;


@Controller
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcService;
	
	@Autowired
	private CargoService cargoService;
	
	@GetMapping(value = "/cadastrar")
	public String cadastrar(Funcionario funcionario, ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		return "funcionario/cadastro";
	}	
	
	@GetMapping(value = "/listar")
	public String listar() {
		return "funcionario/lista";
	}	


}
