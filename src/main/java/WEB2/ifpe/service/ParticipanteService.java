package WEB2.ifpe.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import WEB2.ifpe.model.Participante;
import WEB2.ifpe.persistence.ParticipanteDAO;
import WEB2.ifpe.util.Util;

@Service
public class ParticipanteService {


	@Autowired
	private ParticipanteDAO participanteDAO;
	
	public List<Participante>listarTodos(Sort ordenacao) {
		return this.participanteDAO.findAll(ordenacao);
	}
	
	public Participante obterPorId(Integer idParticipante) {
		return this.participanteDAO.getOne(idParticipante);
	}
	
	public Participante findParticipanteByEmail(String email) {
		return participanteDAO.findByEmailIgnoreCase(email);
	}
	
	public Participante findParticipanteByCpf(String cpf) {
		return participanteDAO.findByCpfIgnoreCase(cpf);
	}
	
	public void save(Participante participante) {
		this.participanteDAO.save(participante);
	}
	
public boolean salvarParticipante(Participante participante)throws ServiceException, MessagingException {
		
	// Verificar a existencia de um participante com o cpf
	
	if (this.findParticipanteByEmail(participante.getEmail()) != null) {
		throw new ServiceException("Já existe um usuário com este e-mail");
	} 
	else if (this.findParticipanteByCpf(participante.getCpf()) != null) {
		throw new ServiceException("Já existe um usuário com este cpf");
	}  else {
			String senhaCriptografada;
			try {
				senhaCriptografada = Util.criptografarSenha(participante.getSenha());
				participante.setSenha(senhaCriptografada);
				this.participanteDAO.save(participante);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			this.save(participante);
			return true;
       
	}	
	 
}

public Participante logarParticipante(String email, String senha) throws ServiceException, NoSuchAlgorithmException, UnsupportedEncodingException {	
	
	String senhaCriptografada = Util.criptografarSenha(senha);
	Participante participante = this.participanteDAO.participanteLogin(email, senhaCriptografada);

	if (participante == null) {
		throw new ServiceException("Login/senha não encontrados");
	}

	return participante;
}

	
}
