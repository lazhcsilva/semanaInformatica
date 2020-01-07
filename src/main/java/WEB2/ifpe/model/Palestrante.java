package WEB2.ifpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Palestrante {

	@Id @GeneratedValue
	private Integer idPalestrante;
	
	@NotBlank(message = "Nome deve ser preenchido")
	@Column(length = 50)
	private String nome;
	
	@NotBlank(message = "Email deve ser preenchido")
	@Column(length = 60)
	private String email;

	public Integer getIdPalestrante() {
		return idPalestrante;
	}

	public void setIdPalestrante(Integer idPalestrante) {
		this.idPalestrante = idPalestrante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
