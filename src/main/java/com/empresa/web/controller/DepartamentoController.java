package com.empresa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", depService.buscarTodos());
		return "departamento/lista";
	}	
	
	@PostMapping(value = "/salvar")
	public String salvar(Departamento departamento, RedirectAttributes attr) {
		depService.salvar(departamento);	
		attr.addFlashAttribute("success", "Departamento cadastrado!");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping(value = "/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("departamento", depService.buscarPorId(id).get());
		return "/departamento/cadastro";
	}
	
	@PostMapping(value = "/editar")
	public String editar(Departamento departamento, RedirectAttributes attr) {
		depService.editar(departamento);
		attr.addFlashAttribute("success", "Departamento editado!");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable Long id, ModelMap model) {
		if(!depService.departamentoTemCargo(id)) {
			depService.excluir(id);
			model.addAttribute("success", "Departamento exluido!");
		}else {
			model.addAttribute("fail", "Departamento não pode ser excluido. Possui cargos vinculados.");
		}
		
		return listar(model);
	}


}
