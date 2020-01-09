package WEB2.ifpe.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import WEB2.ifpe.model.Palestrante;

public interface PalestranteDAO extends JpaRepository<Palestrante, Integer> {

<<<<<<< HEAD
	@Query("select p from Palestrante p where p.email = :email and p.senha = :senha")
	public Palestrante palestranteLogin(String email, String senha);
=======
	//Palestrante buscaLogin(String email, String senha);
>>>>>>> branch 'master' of https://github.com/lazhcsilva/semanaInformatica
	
	@Query("select p from Palestrante p where p.email = :email")
	public Palestrante findByEmail(String email);
	
	@Query("select p from Palestrante p where p.cpf = :cpf")
	public Palestrante findClienteByCpf(String cpf);
}
