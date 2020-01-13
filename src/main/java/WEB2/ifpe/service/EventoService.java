package WEB2.ifpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import WEB2.ifpe.model.Evento;
import WEB2.ifpe.persistence.EventoDAO;

@Service
public class EventoService {

	@Autowired
	private EventoDAO eventoDAO;
	
	
	public List<Evento>listarTodos(Sort ordenacao) {
		return this.eventoDAO.findAll(ordenacao);
	}

	public Evento obterPorId(Integer idEvento) {
		return this.eventoDAO.getOne(idEvento);
	}
	
	public void remover(Integer idEvento) {
		this.eventoDAO.deleteById(idEvento);
	}


	

	public boolean salvarEvento(Evento evento) {
		
		Evento eventoComNomeAtividadeExistente = this.eventoDAO.findByNomeAtividade(evento.getNomeAtividade());
		
		if (eventoComNomeAtividadeExistente == null) {
			this.eventoDAO.save(evento);	
			return true;
		}
		return false;	
	} 
	
	public void verificarHosrarioEvento(Evento evento) {
		
	}
	
}
