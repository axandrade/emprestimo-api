package br.uece.sigdp.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.uece.sigdp.entity.enums.StatusPagamento;

public class EmprestimoDTO {

	private Long id;
	private PessoaDTO pessoa;
	private BigDecimal valorEmprestimo;
	private int numeroParcelas;
	private StatusPagamento statusPagamento;
	private LocalDate dataCriacao;

	public EmprestimoDTO() {
	}

	public EmprestimoDTO(Long id, PessoaDTO pessoa, BigDecimal valorEmprestimo, int numeroParcelas,
			StatusPagamento statusPagamento, LocalDate dataCriacao) {
		this.id = id;
		this.pessoa = pessoa;
		this.valorEmprestimo = valorEmprestimo;
		this.numeroParcelas = numeroParcelas;
		this.statusPagamento = statusPagamento;
		this.dataCriacao = dataCriacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public BigDecimal getValorEmprestimo() {
		return valorEmprestimo;
	}

	public void setValorEmprestimo(BigDecimal valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}

	public int getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(int numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
