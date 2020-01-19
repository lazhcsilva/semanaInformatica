package WEB2.ifpe.controller;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import WEB2.ifpe.model.Palestrante;
import WEB2.ifpe.persistence.PalestranteDAO;
import WEB2.ifpe.service.PalestranteService;

@Controller
public class PalestranteController {

	@Autowired
	private PalestranteService palestranteService;
	
	@Autowired
	private PalestranteDAO palestranteDAO;
	
	@GetMapping("/listarPalestrante")
	public String exibirLista(Model model) {
		model.addAttribute("lista", this.palestranteService.listarTodos(Sort.by("nome")));
		return "listas/listar-palestrante";
	}
	
	@GetMapping("/exibirFormPalestrante")
	public String exibirForm(Palestrante palestrante) {
	
		return "cadastros/cadastrar-palestrante";
	
	}	

	
	@PostMapping("/salvarPalestrante")
	public String salvarPalestrante(@Valid Palestrante palestrante, BindingResult br) {
		if (br.hasErrors()) {
			return this.exibirForm(palestrante);
		}
		this.palestranteService.save(palestrante);
		return "redirect:/listarPalestrante";
	}
	
	@GetMapping("/editarPalestrante")
	public String editarPalestrante(Model model, Integer idPalestrante) {
		 model.addAttribute("palestrante", this.palestranteDAO.findById(idPalestrante));
		 return "cadastros/cadastrar-palestrante";
		 
	}
	
	@GetMapping("/removerPalestrante")
	public String removerPalestrante(Integer idPalestrante) {
		this.palestranteService.remover(idPalestrante);
		return "redirect:/listarPalestrante";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession sessao) {
		
		sessao.invalidate();
		return "redirect:/index";
	
	}
	
	@PostMapping("/login")
	public String palestranteLogin(HttpServletRequest request, @ModelAttribute Palestrante palestrante, @RequestParam(name = "retorno", required = false) String retorno, RedirectAttributes ra, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String redirect = "redirect:/index";
		if (retorno != null) {
			redirect = "redirect:" + retorno;
		}

		Palestrante palestranteLogado;
		try {
			palestranteLogado = this.palestranteService.logarPalestrante(palestrante.getEmail(), palestrante.getSenha());
			session.setAttribute("usuarioLogado", palestranteLogado);
		} catch (ServiceException e) {
			ra.addFlashAttribute("mensagemErro", e.getMessage());
		}

		ra.addFlashAttribute("loginEfetuado", true);
		return redirect;
	}
	
}
