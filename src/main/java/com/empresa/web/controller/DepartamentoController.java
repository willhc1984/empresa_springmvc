package com.empresa.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String listar(ModelMap model, @RequestParam(name = "page", defaultValue = "1") int page, 
			@RequestParam(name = "size", defaultValue = "8") int size) {
			
		Pageable paging = PageRequest.of(page - 1, size);		
		Page<Departamento> departamentos = depService.buscarTodos(paging);		
		model.addAttribute("departamentos", departamentos);
		
		return "departamento/lista";
	}	
	
	@PostMapping(value = "/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result, 
			RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/departamento/cadastro";
		}
		
		try {
			depService.salvar(departamento);	
			attr.addFlashAttribute("success", "Departamento cadastrado!");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Departamento já existente!");
		}
		
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping(value = "/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("departamento", depService.buscarPorId(id).get());
		return "/departamento/cadastro";
	}
	
	@PostMapping(value = "/editar")
	public String editar(@Valid Departamento departamento, BindingResult result, 
			RedirectAttributes attr, ModelMap model) {
		
		if(result.hasErrors()) {
			return "/departamento/cadastro";
		}
		
		try {
			depService.editar(departamento);
			attr.addFlashAttribute("success", "Departamento editado!");
		} catch (Exception e) {
			model.addAttribute("fail", "Departamento já existente!");
			return "/departamento/cadastro";
		}
		
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable Long id, ModelMap model, RedirectAttributes attr) {
		
		if(!depService.departamentoTemCargo(id)) {
			depService.excluir(id);
			attr.addFlashAttribute("success", "Departamento excluido!");
			return "redirect:/departamentos/listar";
		}
		
		attr.addFlashAttribute("fail", "Departamento não excluido! Possui cargo vinculado.");
		return "redirect:/departamentos/listar";
	}


}
