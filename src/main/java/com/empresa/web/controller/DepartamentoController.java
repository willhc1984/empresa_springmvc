package com.empresa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.domain.Departamento;
import com.empresa.service.DepartamentoService;


@Controller
@RequestMapping(value = "/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService depService;
	
	@GetMapping(value = "/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "departamento/cadastro";
	}	
	
	@GetMapping(value = "/listar")
	public String listar() {
		return "departamento/lista";
	}	
	
	@PostMapping(value = "/salvar")
	public String salvar(Departamento departamento) {
		depService.salvar(departamento);
		return "redirect:/departamentos/cadastrar";
	}


}
