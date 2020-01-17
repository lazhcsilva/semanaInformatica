package WEB2.ifpe.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
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


	

	public Evento salvarEvento(Evento evento){
		
		this.checarRegras(evento);
		evento = eventoDAO.save(evento);
		return evento;
	} 
	
	public void checarRegras(Evento evento) throws ServiceException {
		
		/**verificar se o nome da atividade já existe**/
		Evento eventoBusca = eventoDAO.findByNomeAtividade(evento.getNomeAtividade()); 
		
		if(eventoBusca != null) {
			throw new ServiceException("Já existe um evento com esse nome");
		}
		
//		/**verifica se existe uma sala usada no horário**/
//		if(eventoBusca.getSala().getNumero().equals(evento.getSala().getNumero())
//				&& eventoBusca.get) {
//			
//		}
			
	}
	
}
