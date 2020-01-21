package WEB2.ifpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import WEB2.ifpe.enums.TipoPalestranteEnum;

@Entity
public class Palestrante {

	@Id @GeneratedValue
	private Integer idPalestrante;
	
	private String nome;
	
	@NotBlank(message = "Email deve ser preenchido")
	@Column(length = 60)
	private String email;
	
	@NotBlank(message = "Senha deve ser preenchida")
	private String senha;
	
	private String cpf;
	
	@ManyToOne
	private Evento evento;

	@Enumerated(EnumType.ORDINAL)
	private TipoPalestranteEnum tipoPalestrante = TipoPalestranteEnum.PADRAO;
	
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoPalestranteEnum getTipoPalestrante() {
		return tipoPalestrante;
	}

	public void setTipoPalestrante(TipoPalestranteEnum tipoPalestrante) {
		this.tipoPalestrante = tipoPalestrante;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
	
}
