package WEB2.ifpe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import WEB2.ifpe.model.Sala;
import WEB2.ifpe.service.SalaService;

@Controller
public class SalaController {

	@Autowired
	private SalaService salaService;
	
	
	@GetMapping("/listarSala")
	public String exibirLista(Model model) {
		model.addAttribute("lista", this.salaService.listarTodos(Sort.by("numero")));
		return "listas/listar-evento";
	}

	@GetMapping("/exibirFormSala")
	public String exibirForm(Sala sala) {
		return "cadastros/cadastrar-sala";
	}
	
	@GetMapping("/editarSala")
	public String editarSala(Integer idSala, Model model) {
		model.addAttribute("sala", this.salaService.obterPorId(idSala));
		return "redirect:/exibirFormSala";
	}
	
	@GetMapping("/removerSala")
	public String removerSala(Integer idSala) {
		this.salaService.remover(idSala);
		return "redirect:/listarSala";
	}
	
	@PostMapping("/salvarSala")
	public String salvarSala(@Valid Sala sala, BindingResult br, RedirectAttributes ra, Model model) {      
		
		if (br.hasErrors()) {
		
			return this.exibirForm(sala);
		
		}

		this.salaService.novaSala(sala);

		return "redirect:/listarSala";
		
	}
}
