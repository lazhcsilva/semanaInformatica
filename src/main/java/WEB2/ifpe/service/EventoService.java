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
		Evento eventoBuscaByName = eventoDAO.findByNomeAtividade(evento.getNomeAtividade()); 
		
		if(eventoBuscaByName != null) {
			throw new ServiceException("Já existe um evento com esse nome");
		}
		
		/**verifica o inio e o fim da palestra**/
		if(evento.getHoraInicio().isAfter(evento.getHoraFim())) {
			throw new ServiceException("Hora do início do evento não pode ser maior que o horário do fim");
		}
		
		/**verifica se já existe uma palestra em dado horário**/
//		List<Evento> eventoByDate = eventoDAO.findByDataAndSala(evento.getData(), evento.getSala().getIdSala());
//		for (Evento eventoIndex : eventoByDate) {
//			if (eventoIndex.getHoraInicio().isAfter(evento.getHoraInicio())
//					&& eventoIndex.getHoraInicio().isBefore(evento.getHoraFim())
//					|| eventoIndex.getHoraFim().isAfter(evento.getHoraInicio())
//							&& eventoIndex.getHoraFim().isBefore(evento.getHoraFim())) {
//				throw new ServiceException("Já existe uma palestra reservada nesse horário");
//			}
//		}
			
	}
	
}
