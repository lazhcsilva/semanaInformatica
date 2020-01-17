package WEB2.ifpe.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import WEB2.ifpe.model.Sala;

public interface SalaDAO extends JpaRepository<Sala, Integer> {

	

	Sala findByNumero(String numero);

	
}
