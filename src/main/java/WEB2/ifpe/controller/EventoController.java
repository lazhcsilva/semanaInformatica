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

import WEB2.ifpe.model.Evento;
import WEB2.ifpe.service.EventoService;
import WEB2.ifpe.service.PalestranteService;
import WEB2.ifpe.service.SalaService;

@Controller
public class EventoController {

	
	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private PalestranteService palestranteService;
	
	@Autowired
	private SalaService salaService;
		
	@GetMapping("/listarEvento")
	public String exibirLista(Model model) {
		model.addAttribute("lista", this.eventoService.listarTodos(Sort.by("data")));
		return "listas/listar-evento";
	}
	
	@GetMapping("/exibirFormEvento")
	public String exibirForm(Evento evento , Model model) {
		model.addAttribute("listaPalestrante", palestranteService.listarTodos(Sort.by("nome")));
		model.addAttribute("listaSala", salaService.listarTodos(Sort.by("nome")));
		return "cadastros/cadastrar-evento";
	}
	
	@GetMapping("/editarEvento")
	public String editarProduto(Integer idEvento, Model model) {
		model.addAttribute("evento", this.eventoService.obterPorId(idEvento));
		model.addAttribute("listaPalestrante", this.palestranteService.listarTodos(Sort.by("nome")));
		model.addAttribute("listaSala", this.salaService.listarTodos(Sort.by("nome")));
		return "evento/evento-form";
	}
	
	@PostMapping("/salvarEvento")
	public String salvarEvento(@Valid Evento evento, BindingResult br, RedirectAttributes ra, Model model) {
	
		this.eventoService.salvarEvento(evento);
		return "redirect:/listarPalestrante";
	
	}

	
	
}