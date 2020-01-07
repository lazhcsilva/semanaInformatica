package WEB2.ifpe.controller;

import javax.mail.MessagingException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import WEB2.ifpe.model.Participante;
import WEB2.ifpe.service.ParticipanteService;



@Controller
public class ParticipanteController {

	@Autowired
	private ParticipanteService participanteService;
	
	
	 
	@GetMapping("/listarParticipante")
	public String exibirLista(Model model) {
		model.addAttribute("lista", this.participanteService.listarTodos(Sort.by("nome")));
		return "listas/listar-participante";
	}
	
	@GetMapping("/exibirFormParticipante")
	public String exibirForm(Participante participante) {
		return "/cadastro";
	}
	
	/*@GetMapping("/editarParticipante")
	public String editarParticipante(Integer idParticipante, Model model) {
		model.addAttribute("participante", this.participanteService.obterPorId(idParticipante));
		return "participante/participante-form";
	}*/
	
	@PostMapping("/salvarParticipante")
	public String salvarParticipante(@Valid Participante participante, 
			BindingResult br, RedirectAttributes ra,Errors errors) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível criar usuário: " + errors.getFieldErrors());

			return "redirect:/exibirFormParticipante";
		} else {
			try {
				this.participanteService.salvarParticipante(participante);
				ra.addFlashAttribute("mensagem", "Conta criada com sucesso!");
			} catch (ServiceException | MessagingException e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível criar usuário: " + e.getMessage());

				return "redirect:/exibirFormParticipante";
			}
		}
		ra.addFlashAttribute("contaCriada", true);
		return "redirect:/index";
	}
	
	
	
	/*@GetMapping("/removerParticipante")
	public String removerParticipante(Integer idParticipante) {
		this.participanteService.remover(idParticipante);
		return "redirect:/listarParticipante";
	}*/
}
