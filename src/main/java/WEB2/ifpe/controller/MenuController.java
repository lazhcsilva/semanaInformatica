package WEB2.ifpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import WEB2.ifpe.model.Evento;
import WEB2.ifpe.service.EventoService;

@Controller
public class MenuController {

	@Autowired
	private EventoService eventoService;
	
	@GetMapping("/index")
	public String index(){
		
		return "index";
		
	}
	
	@GetMapping("/paginaInicial")
	public String paginainicial() {
	
		return "paginainicial";
		
	}
	
	@GetMapping("/programacao")
	public String programacao(Evento evento , Model model) {
		
		model.addAttribute("lista", this.eventoService.listarTodos(Sort.by("nomeAtividade")));
		return "programacao";
		
	}
	
	@GetMapping("/gestor")
	public String gestor() {
		
		return "gestor";
		
	}
	
}