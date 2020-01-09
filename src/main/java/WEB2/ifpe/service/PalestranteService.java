package WEB2.ifpe.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import WEB2.ifpe.model.Palestrante;
import WEB2.ifpe.persistence.PalestranteDAO;
import WEB2.ifpe.util.Util;

@Service
public class PalestranteService {

	@Autowired
	private PalestranteDAO palestranteDAO;
	
	public List<Palestrante>listarTodos(Sort ordenacao) {
		return this.palestranteDAO.findAll(ordenacao);
	}
	
	public Palestrante obterPorId(Integer idPalestrante) {
		return this.palestranteDAO.getOne(idPalestrante);
	}
	
	public boolean salvarPalestrante(Palestrante palestrante) {
		
		// Verificar a existencia de um Palestrante com o email
		
		Palestrante palestranteComEmailExistente = this.palestranteDAO.findByEmail(palestrante.getEmail());
		
		if (palestranteComEmailExistente == null) {
			this.palestranteDAO.save(palestrante);	
			return true;
		}
		return false;	
	}
	
	public Palestrante logarPalestrante(String email, String senha) throws ServiceException, NoSuchAlgorithmException, UnsupportedEncodingException {	
		
		String senhaCriptografada = Util.criptografarSenha(senha);
		Palestrante palestrante = this.palestranteDAO.palestranteLogin(email, senhaCriptografada);

		if (palestrante == null) {
			throw new ServiceException("Login/senha não encontrados");
		}

		return palestrante;
	}
		

	public void remover(Integer idPalestrante) {
		this.palestranteDAO.deleteById(idPalestrante);
	}
}
