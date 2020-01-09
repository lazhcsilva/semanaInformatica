package WEB2.ifpe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class LoginController {

	/*
	@Autowired
	private PalestranteDAO palestranteDAO;
	
	@PostMapping("/palestranteLogin")
	public String fazerLogin(@ModelAttribute("palestrante") Palestrante palestrante, BindingResult br, Model model, HttpSession sessao) {
		
		palestrante = palestranteDAO.buscaLogin(palestrante.getEmail(), palestrante.getSenha());
		
		if(palestrante == null) {
			
			model.addAttribute("mensagem", "Email e senha invalido");
			
		} else {
			
			sessao.setAttribute("palestranteLogado", palestrante);		
			return "/paginaInicial";
			
		}
		
		return "/index";
	}*/
	
	@PostMapping("/logout")
	public String logout(HttpSession sessao) {
		
		sessao.invalidate();
		return "redirect:/index";
	
	}
	
}
