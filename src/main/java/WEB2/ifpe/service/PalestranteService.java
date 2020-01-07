package WEB2.ifpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import WEB2.ifpe.model.Palestrante;
import WEB2.ifpe.persistence.PalestranteDAO;

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
	
	/*public boolean salvarPalestrante(Palestrante palestrante) {
		
		// Verificar a existencia de um Palestrante com o email
		
		Palestrante palestranteComEmailExistente = this.palestranteDAO
				.findByEmail(palestrante.getEmail());
		
		if (palestranteComEmailExistente == null) {
			this.palestranteDAO.save(palestrante);	
			return true;
		}
		return false;	
	}*/
		

	public void remover(Integer idPalestrante) {
		this.palestranteDAO.deleteById(idPalestrante);
	}
}
