package br.uece.sigdp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import br.uece.sigdp.entity.enums.TipoIdentificador;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "pessoas", schema = "emprestimo")
public class Pessoa implements Serializable{
			
	private static final long serialVersionUID = -1723700870639006755L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
    
    @Column(name = "identificador", nullable = false, length = 20)
    @NotBlank(message = "O identificador é obrigatório.")
    private String identificador;
    
    @Column(name = "data_nascimento")
    @Past(message = "A data de nascimento deve ser no passado.")
    private LocalDate dataNascimento;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificador", nullable = false, length = 20)
    @NotNull(message = "O tipo de identificador é obrigatório.")
    private TipoIdentificador tipoIdentificador;
    
    @Column(name = "valor_minimo_mensal_parcelas", precision = 15, scale = 2)
    @DecimalMin(value = "0.00", inclusive = false, message = "O valor mínimo mensal das parcelas deve ser maior que zero.")
    private BigDecimal valorMinimoMensalParcelas;
    
    @Column(name = "valor_maximo_emprestimo", precision = 15, scale = 2)
    @DecimalMin(value = "0.00", inclusive = false, message = "O valor máximo de empréstimo deve ser maior que zero.")
    private BigDecimal valorMaximoEmprestimo;
    
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprestimo> emprestimos;
	
    public Pessoa() {
	}

	public Pessoa(Long id, String nome, String identificador, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		setIdentificador(identificador);
		this.dataNascimento = dataNascimento;
		this.valorMinimoMensalParcelas = this.tipoIdentificador.getValorMinimoMensalParcelas();
        this.valorMaximoEmprestimo = this.tipoIdentificador.getValorMaximoEmprestimo();
    
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
		if (identificador == null) {
			throw new IllegalArgumentException("O identificador não pode ser nulo.");
		}
		this.identificador = identificador;
		this.tipoIdentificador = TipoIdentificador.fromLength(identificador.length());
		this.valorMinimoMensalParcelas = this.tipoIdentificador.getValorMinimoMensalParcelas();
        this.valorMaximoEmprestimo = this.tipoIdentificador.getValorMaximoEmprestimo();    
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoIdentificador getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(TipoIdentificador tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	public BigDecimal getValorMinimoMensalParcelas() {
		return valorMinimoMensalParcelas;
	}

	public void setValorMinimoMensalParcelas(BigDecimal valorMinimoMensalParcelas) {
		this.valorMinimoMensalParcelas = valorMinimoMensalParcelas;
	}

	public BigDecimal getValorMaximoEmprestimo() {
		return valorMaximoEmprestimo;
	}

	public void setValorMaximoEmprestimo(BigDecimal valorMaximoEmprestimo) {
		this.valorMaximoEmprestimo = valorMaximoEmprestimo;
	}	

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}