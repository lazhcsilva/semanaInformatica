package WEB2.ifpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Sala {


	@Id @GeneratedValue
	private Integer idSala;
	
	@NotBlank(message = "O Numero Da Sala deve ser preenchido")
	@Column(length = 4)
	@Size(min = 1, max = 4, message = "o numero maximo para sala é 9999")
	private String numero;
	
	
	@Min(value = 1, message = "Capacidade deve ser informada, no min 1")
	@Max(value = 9999, message = "Capacidade deve ter no maximo até 9999 ")
	private int capacidade;

	public Integer getIdSala() {
		return idSala;
	}

	public void setIdSala(Integer idSala) {
		this.idSala = idSala;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

}
