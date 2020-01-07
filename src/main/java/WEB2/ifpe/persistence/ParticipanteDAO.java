package WEB2.ifpe.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import WEB2.ifpe.model.Participante;

public interface ParticipanteDAO extends JpaRepository<Participante, Integer> {

	
	public Participante findByEmailIgnoreCase(String email);
	
	public Participante findByCpfIgnoreCase(String cpf);

}
