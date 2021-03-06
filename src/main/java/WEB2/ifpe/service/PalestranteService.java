package WEB2.ifpe.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import WEB2.ifpe.enums.TipoPalestranteEnum;
import WEB2.ifpe.model.Palestrante;
import WEB2.ifpe.persistence.PalestranteDAO;
import WEB2.ifpe.util.Util;

@Service
public class PalestranteService {

	@Autowired
	private PalestranteDAO palestranteDAO;
	
	public List<Palestrante>listarTodos(Sort ordenacao) {
		List<Palestrante> palestra = palestranteDAO.findAll(ordenacao);
		return palestra;
	}
	
	public Palestrante obterPorId(Integer idPalestrante) {
		return this.palestranteDAO.getOne(idPalestrante);
	}
	
	public boolean criarPalestrante(Palestrante palestrante) throws ServiceException, MessagingException {
		
		String senhaCriptografada;
		try {
			
			senhaCriptografada = Util.criptografarSenha(palestrante.getSenha());
			palestrante.setSenha(senhaCriptografada);
			this.palestranteDAO.save(palestrante);	
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		palestrante.setTipoPalestrante(TipoPalestranteEnum.PADRAO);
		this.save(palestrante);
		return true;
	
	}
	
	public Palestrante logarPalestrante(String email, String senha) throws ServiceException, NoSuchAlgorithmException, UnsupportedEncodingException {	
		
		String senhaCriptografada = Util.criptografarSenha(senha);
		Palestrante palestrante = this.palestranteDAO.palestranteLogin(email, senhaCriptografada);

		if (palestrante == null) {
			throw new ServiceException("Login/senha não encontrados");
		}

		return palestrante;
	}
	
	public void save(Palestrante palestrante) {
		this.palestranteDAO.save(palestrante);
	}

	public void remover(Integer idPalestrante) {
		this.palestranteDAO.deleteById(idPalestrante);
	}
}
