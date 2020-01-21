package WEB2.ifpe.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import WEB2.ifpe.model.Evento;

public interface EventoDAO extends JpaRepository<Evento, Integer> {

	Evento findByNomeAtividade(String nomeAtividade);
	
	List<Evento> findByDataAndSala(LocalDate data, Integer idSala);
}
