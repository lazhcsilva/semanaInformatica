package WEB2.ifpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Participante {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idParticipante;
	
	@Column(length = 50)
	@NotBlank(message = "Nome completo deve ser preenchido")
	@Size(min = 3, max = 50, message = "Nome completo deve ter entre 3 a 50 letras")
	private String nome;
	
	@Column(length = 50)
	@NotBlank(message = "Nome completo deve ser preenchido")
	@Size(min = 9, max = 50, message = "email deve ter entre 9 a 50 letras")
	private String email;
	
	@Column(length = 14, nullable = false)
	@NotBlank(message = "CPF deve ser preenchido")
	private String cpf;
	
	@Column(length = 50)
	@Size(min = 5, max = 100, message = "senha deve ter entre 5 a 20 digitos")
	@NotBlank(message = "senha deve ser preenchida")
	private String senha;
	
	private String token;
	

	public Integer getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Integer idParticipante) {
		this.idParticipante = idParticipante;
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
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
		
}
