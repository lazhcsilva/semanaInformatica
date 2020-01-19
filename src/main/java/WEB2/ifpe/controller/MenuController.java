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
	
	@GetMapping("/programacao")
	public String programacao() {
	
		return "programacao";
		
	}
	
	@GetMapping("/gestor")
	public String gestor() {
		
		return "gestor";
		
	}
	
}