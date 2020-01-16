package WEB2.ifpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import WEB2.ifpe.model.Sala;
import WEB2.ifpe.persistence.SalaDAO;

@Service
public class SalaService {

	@Autowired
	private SalaDAO salaDAO;
	
	public List<Sala>listarTodos(Sort ordenacao) {
		return this.salaDAO.findAll(ordenacao);
	}
	
	public Sala obterPorId(Integer idSala) {
		return this.salaDAO.getOne(idSala);
	}
	
	public void remover(Integer idSala) {
		// TODO Auto-generated method stub
		this.salaDAO.deleteById(idSala);
		
	}
	
	public boolean salvarSala(Sala sala) {
		
		Sala SalaComNumeroExistente = this.salaDAO.findByNumero(sala.getNumero());
		
		if (SalaComNumeroExistente == null) {
			this.salaDAO.save(sala);	
			return true;
		}
		return false;	
	}
}
