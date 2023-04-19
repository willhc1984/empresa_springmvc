package com.empresa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		return "cargo/lista";
	}	
	
	@PostMapping(value = "/editar")
	public String editar(Cargo cargo, RedirectAttributes attr) {
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Cargo editado!");
		return "redirect:/cargos/cadastrar";
	}
	
	@PostMapping(value = "/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attr) {
		System.out.println(cargo);
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo cadastrado!");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		cargoService.excluir(id);
		attr.addFlashAttribute("success", "Cargo excluido!");
		return "redirect:/cargos/listar";
	}

}
