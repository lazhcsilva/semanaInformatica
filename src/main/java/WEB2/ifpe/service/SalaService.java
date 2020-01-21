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
		
		this.salaDAO.deleteById(idSala);
		
	}
	
	public Sala novaSala(Sala sala){
		
		Sala SalaComNumeroExistente = this.salaDAO.findByNome(sala.getNome());
		
		if (SalaComNumeroExistente == null) {
			this.save(sala);
			return (sala);
		}
		
		return sala;
	}
	
	public void save(Sala sala) {
	
		this.salaDAO.save(sala);
	
	}
	
}