package WEB2.ifpe.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import WEB2.ifpe.model.Participante;
import WEB2.ifpe.service.ParticipanteService;



@Controller
public class ParticipanteController {

	@Autowired
	private ParticipanteService participanteService;
	
	@PostMapping("/participanteLogin")
	public String participanteLogin(HttpServletRequest request, Participante particpanteAtributos, @RequestParam(name = "retorno", required = false) String retorno, RedirectAttributes ra, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String redirect = "redirect:/index";
		if (retorno != null) {
			redirect = "redirect:" + retorno;
		}

		Participante participanteLogado;
		try {
			participanteLogado = this.participanteService.logarParticipante(particpanteAtributos.getEmail(), particpanteAtributos.getSenha());
			session.setAttribute("usuarioLogado", participanteLogado);
			ra.addFlashAttribute("mensagem", "logado");

		} catch (ServiceException e) {

			ra.addFlashAttribute("mensagem", e.getMessage());
			System.out.println(e.getMessage());
			ra.addFlashAttribute("particpanteAtributos", particpanteAtributos);

			return "redirect:/login";
		}
		

		ra.addFlashAttribute("loginEfetuado", true);
		return redirect;
	}
	
	@GetMapping("/login")
	public String login(Participante participante) {
	
		return "login";
	
	}
	@GetMapping("/deslogar")
	public String deslogar(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	 
	@GetMapping("/listarParticipante")
	public String exibirLista(Model model) {
		model.addAttribute("lista", this.participanteService.listarTodos(Sort.by("nome")));
		return "listas/listar-participante";
	}
	
	@GetMapping("/exibirFormParticipante")
	public String exibirForm(Participante participante) {
		return "/cadastro";
	}
	
	@GetMapping("/editarParticipante")
	public String editarParticipante(Integer idParticipante, Model model) {
		model.addAttribute("participante", this.participanteService.obterPorId(idParticipante));
		return "participante/participante-form";
	}
	
	@PostMapping("/salvarParticipante")
	public String salvarParticipante(@Valid Participante participante, 
			BindingResult br,Model model, RedirectAttributes ra,Errors errors) {
		
		if (errors.hasErrors()) {
			ra.addFlashAttribute("menssage", "erro");
			return this.exibirForm(participante);
		} else {
			try {
				this.participanteService.salvarParticipante(participante);

			} catch (ServiceException | MessagingException e) {
				ra.addFlashAttribute("menssage", "Não foi possível criar usuário: " + e.getMessage());
                ra.addFlashAttribute("participante", participante);
				return "redirect:/exibirFormParticipante";
			}
			    ra.addFlashAttribute("menssage", "Conta criada com sucesso!");
		}
		return "redirect:/exibirFormParticipante";
	}
	
	
	/*@GetMapping("/removerParticipante")
	public String removerParticipante(Integer idParticipante) {
		this.participanteService.remover(idParticipante);
		return "redirect:/listarParticipante";
	}*/
}
