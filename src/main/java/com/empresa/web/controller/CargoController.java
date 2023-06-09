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

import com.empresa.domain.Cargo;
import com.empresa.service.CargoService;
import com.empresa.service.DepartamentoService;


@Controller
@RequestMapping(value = "/cargos")
public class CargoController {
	
	@Autowired
	private DepartamentoService depService;
	
	@Autowired
	private CargoService cargoService;
	
	@GetMapping(value = "/cadastrar")
	public String cadastrar(ModelMap model) {
		model.addAttribute("departamentos", depService.buscarTodos());
		model.addAttribute("cargo", new Cargo());
		return "cargo/cadastro";
	}	
	
	@GetMapping(value = "/editar/{id}")
	public String editar(ModelMap model, @PathVariable Long id) {
		model.addAttribute("departamentos", depService.buscarTodos());
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}
	
	@GetMapping(value = "/listar")
	public String listar(ModelMap model, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		
		Pageable paging = PageRequest.of(page - 1, size);
		
		Page<Cargo> cargos = cargoService.buscarTodos(paging);		
		model.addAttribute("cargos", cargos);
		
		return "cargo/lista";
	}	
	
	@PostMapping(value = "/editar")
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr,
			ModelMap model) {
		
		model.addAttribute("departamentos", depService.buscarTodos());
		
		if(result.hasErrors()) {
			return "/cargo/cadastro";
		}
		
		try {
			cargoService.editar(cargo);
			attr.addFlashAttribute("success", "Cargo editado!");
		} catch (Exception e) {
			model.addAttribute("fail", "Nome do cargo ja existente!");
			return "/cargo/cadastro";
		}
		
		return "redirect:/cargos/cadastrar";
	}
	
	@PostMapping(value = "/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, 
			RedirectAttributes attr, ModelMap model) {
		
		model.addAttribute("departamentos", depService.buscarTodos());
		
		if(result.hasErrors()) {
			return "/cargo/cadastro";
		}
		
		try {
			cargoService.editar(cargo);
			attr.addFlashAttribute("success", "Cargo salvo!");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Nome do cargo ja existente!");
		}
		
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		
		if(!cargoService.cargoTemFuncionario(id)) {
			cargoService.excluir(id);
			attr.addFlashAttribute("success", "Cargo excluido!");
			return "redirect:/cargos/listar";
		}
		attr.addFlashAttribute("fail", "Cargo não excluido! Possui funcionario vinculado.");
		return "redirect:/cargos/listar";
		
	}

}
