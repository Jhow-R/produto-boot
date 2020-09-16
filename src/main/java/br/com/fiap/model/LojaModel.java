package br.com.fiap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_LOJA")
public class LojaModel {

	@Id
	@Column(name = "ID_LOJA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOJA_SEQ")
	@SequenceGenerator(name = "LOJA_SEQ", sequenceName = "LOJA_SEQ", allocationSize = 1)
	private long id;

	@Column(name = "NOME_LOJA")
	@NotNull(message = "Nome obrigatório")
	@NotBlank
	@Size(min = 2, max = 50, message = "Nome deve ser entre 2 e 50 caracteres")
	private String nome;

	@Column(name = "URL_LOJA")
	@NotNull(message = "URL obrigatório")
	@NotBlank
	@Size(min = 5, max = 50, message = "Url deve ser entre 5 e 50 caracteres")
	private String url;

	@ManyToMany(mappedBy = "lojas")
	@JsonIgnore
	private List<ProdutoModel> produtos;

	public LojaModel() {
	}

	public LojaModel(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}
}
