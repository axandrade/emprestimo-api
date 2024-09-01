package br.uece.sigdp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import br.uece.sigdp.entity.enums.StatusPagamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "emprestimos", schema = "emprestimo")
public class Emprestimo  implements Serializable{


	private static final long serialVersionUID = 4361975870194916012L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    @NotNull(message = "A pessoa associada ao empréstimo não pode ser nula.")
    private Pessoa pessoa;

    @Column(name = "valor_emprestimo", precision = 18, scale = 4, nullable = false)
    @NotNull(message = "O valor do empréstimo é obrigatório.")
    private BigDecimal valorEmprestimo;

    @Column(name = "numero_parcelas", nullable = false)
    @Positive(message = "O número de parcelas deve ser maior que zero.")
    private int numeroParcelas;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", length = 50, nullable = false)
    @NotNull(message = "O status de pagamento é obrigatório.")
    private StatusPagamento statusPagamento;

    @Column(name = "data_criacao", nullable = false)
    @NotNull(message = "A data de criação é obrigatória.")
    private LocalDate dataCriacao;   
    
    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos;
    
    public Emprestimo() {
	}
    
    public Emprestimo(Pessoa pessoa, BigDecimal valorEmprestimo, int numeroParcelas, StatusPagamento statusPagamento, LocalDate dataCriacao) {
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

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
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
