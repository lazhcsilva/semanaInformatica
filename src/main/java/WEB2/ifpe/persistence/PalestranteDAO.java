package WEB2.ifpe.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import WEB2.ifpe.model.Palestrante;

public interface PalestranteDAO extends JpaRepository<Palestrante, Integer> {

	@Query("select p from Palestrante p where p.email = :email and p.senha = :senha")
	public Palestrante palestranteLogin(String email, String senha);
	
	@Query("select p from Palestrante p where p.email = :email")
	public Palestrante findByEmail(String email);
	
	@Query("select p from Palestrante p where p.cpf = :cpf")
	public Palestrante findClienteByCpf(String cpf);
}
