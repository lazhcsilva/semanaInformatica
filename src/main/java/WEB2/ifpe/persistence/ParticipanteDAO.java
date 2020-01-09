package WEB2.ifpe.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import WEB2.ifpe.model.Participante;

public interface ParticipanteDAO extends JpaRepository<Participante, Integer> {

	@Query("select c from Participante c where c.email = :email and c.senha = :senha")
	public Participante participanteLogin(String email, String senha);
	
	@Query("select c from Participante c where c.email = :email")
	public Participante findByEmailIgnoreCase(String email);
	
	@Query("select c from Participante c where c.cpf = :cpf")
	public Participante findByCpfIgnoreCase(String cpf);

}
