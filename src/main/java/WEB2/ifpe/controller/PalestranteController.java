package WEB2.ifpe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import WEB2.ifpe.model.Palestrante;
import WEB2.ifpe.service.PalestranteService;

@Controller
public class PalestranteController {

	@Autowired
	private PalestranteService palestranteService;
	
	@GetMapping("/listarPalestrante")
	public String exibirLista(Model model) {
		model.addAttribute("lista", this.palestranteService.listarTodos(Sort.by("nome")));
		return "listas/listar-palestrante";
	}
	
	@GetMapping("/exibirFormPalestrante")
	public String exibirForm(Palestrante palestrante) {
		return "cadastros/cadastrar-palestrante";
	}
	
	@GetMapping("/editarPalestrante")
	public String editarPalestrante(Integer idPalestrante, Model model) {
		model.addAttribute("palestrante", this.palestranteService.obterPorId(idPalestrante));
		return "redirect:/exibirFormPalestrante";
	}
	
	
	/*@PostMapping("/salvarPalestrante")
	public String salvarPalestrante(@Valid Palestrante palestrante, 
			BindingResult br, RedirectAttributes ra, Model model) {
		if (br.hasErrors()) {
			return this.exibirForm(palestrante);
		}
		boolean retorno = 
				this.palestranteService.salvarPalestrante(palestrante);
			if (retorno == false) {
				model.addAttribute("mensagem", "Já existe um Palestrante com este email");
				return this.exibirForm(palestrante);
			}
			return "redirect:/listarPalestrante";
	}*/
	
	@GetMapping("/removerPalestrante")
	public String removerPalestrante(Integer idPalestrante) {
		this.palestranteService.remover(idPalestrante);
		return "redirect:/listarPalestrante";
	}
}
