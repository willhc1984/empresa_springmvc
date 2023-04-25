package com.empresa.web.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
