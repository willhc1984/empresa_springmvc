package com.empresa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
	
	@GetMapping(value = "/cadastrar")
	public String cadastrar() {
		return "funcionario/cadastro";
	}	
	
	@GetMapping(value = "/listar")
	public String listar() {
		return "funcionario/lista";
	}	


}
