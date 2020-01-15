package WEB2.ifpe.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Evento {

	
	@Id @GeneratedValue(strategy =GenerationType.AUTO)
	private Integer idEvento;
	
	@NotBlank(message = "Nome da Atividade deve ser preenchido")
	private String nomeAtividade;
	
    private LocalDate data;
	
	@ManyToOne
	@NotNull(message = "Palestrante deve ser selecionado")
	private Palestrante palestrante;
	
	@Size(min = 0, max = 150, message = "A descricao tem o limite de 150 letras")
	private String descricao;
	
	@Min(value = 0, message = "vagas disponiveis deve ser informada")
	@Max(value = 9999, message = "vagas disponiveis deve ter no maximo até 9999 ")
	private int vagasDisponiveis;
	
	@ManyToOne
	@NotNull(message = "A sala deve ser selecionada")
	private Sala sala;

	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public Palestrante getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}

	public void setVagasDisponiveis(int vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
