package br.uece.sigdp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "emprestimos")
public class Emprestimo  implements Serializable{


	private static final long serialVersionUID = 4361975870194916012L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    @Column(name = "valor_emprestimo", precision = 18, scale = 4, nullable = false)
    private BigDecimal valorEmprestimo;

    @Column(name = "numero_parcelas", nullable = false)
    private int numeroParcelas;

    @Column(name = "status_pagamento", length = 50, nullable = false)
    private String statusPagamento;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;
    
    public Emprestimo(Pessoa pessoa, BigDecimal valorEmprestimo, int numeroParcelas, String statusPagamento, LocalDate dataCriacao) {
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
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

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
}
