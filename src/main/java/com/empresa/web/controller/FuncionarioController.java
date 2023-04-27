package com.empresa.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.domain.Funcionario;
import com.empresa.domain.UF;
import com.empresa.service.CargoService;
import com.empresa.service.FuncionarioService;


@Controller
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcService;
	
	@Autowired
	private CargoService cargoService;
	
	public UF[] getUFs() {
		return UF.values();
	}
	
	@GetMapping(value = "/cadastrar")
	public String cadastrar(Funcionario funcionario, ModelMap model) {
		model.addAttribute("ufs", getUFs());
		model.addAttribute("cargos", cargoService.buscarTodos());
		return "funcionario/cadastro";
	}	
	
	@GetMapping(value = "/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		model.addAttribute("funcionarios", funcService.buscarTodos());
		return "funcionario/lista";
	}	
	
	@PostMapping(value = "/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionario cadastrado!");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@PostMapping(value = "/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		funcService.editar(funcionario);
		attr.addFlashAttribute("success", "Funcionario editado!");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping(value = "/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("funcionario", funcService.buscarPorId(id));
		model.addAttribute("cargos", cargoService.buscarTodos());
		model.addAttribute("ufs", UF.values());
		return "/funcionario/cadastro";		
	}
	
	@GetMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		funcService.excluir(id);
		attr.addFlashAttribute("success", "Funcionario excluido");
		return "redirect:/funcionarios/listar";
	}
	
	@GetMapping(value = "/buscar/nome")
	public String buscarPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", funcService.buscartPorNome(nome));
		return "/funcionario/lista";
	}
	
	@GetMapping(value = "/buscar/cargo")
	public String buscarPorCargo(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		model.addAttribute("funcionarios", funcService.buscarPorCargo(id));
		return "/funcionario/lista";
	}
	
	@GetMapping(value = "/buscar/data")
	public String buscarPorData(@RequestParam(name = "entrada", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate entrada,
								@RequestParam(name = "saida", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate saida,
								ModelMap model){
		
		model.addAttribute("funcionarios", funcService.buscarPorDatas(entrada, saida));
		
		System.out.println(entrada);
		System.out.println(saida);
		
		return "/funcionario/lista";
		
	}


}
