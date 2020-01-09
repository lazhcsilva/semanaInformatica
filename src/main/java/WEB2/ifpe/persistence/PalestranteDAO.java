package WEB2.ifpe.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import WEB2.ifpe.model.Palestrante;

public interface PalestranteDAO extends JpaRepository<Palestrante, Integer> {

	//Palestrante buscaLogin(String email, String senha);
	
}
