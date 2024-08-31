package br.uece.sigdp.entity.dto;

public class PessoaDTO {

	private Long id;
	private String nome;
	private String identificador;

	public PessoaDTO() {
	}

	public PessoaDTO(Long id, String nome, String identificador) {
		this.id = id;
		this.nome = nome;
		this.identificador = identificador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

}
