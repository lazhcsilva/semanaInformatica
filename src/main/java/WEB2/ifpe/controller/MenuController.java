package WEB2.ifpe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@GetMapping("/index")
	public String index(){
		
		return "index";
		
	}
	
	@GetMapping("/paginaInicial")
	public String paginainicial() {
	
		return "paginainicial";
		
	}
	
	@GetMapping("/login")
	public String login() {
	
		return "login";
	
	}
	
	
	/*
	@GetMapping("/listarPalestrante")
	public String listarPalestrante() {
		
		return "listas/listar-palestrante";
		
	}

	@GetMapping("/cadastrarPalestrante")
	public String cadastrarpalestrante() {
		
		return "cadastros/cadastrar-palestrante";
	
	}
	
	@GetMapping("/cadastrarParticipante")
	public String cadastrarparticipante() {
	
		return "cadastros/cadastrar-participante";
	
	}
	
	@GetMapping("/listarParticipante")
	public String listarparticipante() {
	
		return "listas/listar-participante";
	
	}
	
	@GetMapping("/cadastrarSala")
	public String cadastrarsala() {
	
		return "cadastros/cadastrar-sala";
	
	}
	
	@GetMapping("/cadastrarEvento")
	public String cadastrarEvento() {
	
		return "cadastros/cadastrar-evento";
	
	}*/
	
}