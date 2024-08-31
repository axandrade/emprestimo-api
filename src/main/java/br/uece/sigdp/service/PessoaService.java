package br.uece.sigdp.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.sigdp.entity.Pessoa;
import br.uece.sigdp.entity.enums.TipoIdentificador;
import br.uece.sigdp.exceptions.EmprestimoInvalidoException;
import br.uece.sigdp.exceptions.IdentificadorInvalidoException;
import br.uece.sigdp.repository.PessoaRepository;
import br.uece.sigdp.validator.IdentificadorValidator;


@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
    private static final int MAXIMO_PARCELAS_PERMITIDAS = 24;

	
	public Optional<Pessoa> findPessoaByIdentificador(String identificador) {
        return pessoaRepository.findByIdentificador(identificador);
    }
	
	public boolean isIdentificadorValido(TipoIdentificador tipo, String identificador) {
	    try {
	        if (tipo == TipoIdentificador.PESSOA_FISICA || tipo == TipoIdentificador.PESSOA_JURIDICA) {
	            IdentificadorValidator.validarCpfCnpj(identificador);
	        } else if (tipo == TipoIdentificador.ESTUDANTE_UNIVERSITARIO) {
	            return IdentificadorValidator.validarEstudanteUniversitario(identificador);
	        } else if (tipo == TipoIdentificador.APOSENTADO) {
	            return IdentificadorValidator.validarAposentado(identificador);
	        }
	    } catch (IdentificadorInvalidoException e) {
	        return false;
	    }
	    return true;
	}
	
	public boolean validarLimitesEmprestimo(Pessoa pessoa, BigDecimal valorEmprestimo, int numeroParcelas) {
	    if (valorEmprestimo.compareTo(pessoa.getValorMaximoEmprestimo()) > 0) {
	        throw new EmprestimoInvalidoException("Valor do empréstimo excede o valor máximo permitido para o tipo de identificador.");
	    }

	    BigDecimal valorParcela = valorEmprestimo.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP);
	    if (valorParcela.compareTo(pessoa.getValorMinimoMensalParcelas()) < 0) {
	        throw new EmprestimoInvalidoException("Valor da parcela é inferior ao valor mínimo permitido para o tipo de identificador.");
	    }

	    if (numeroParcelas > MAXIMO_PARCELAS_PERMITIDAS) {
	        throw new EmprestimoInvalidoException("Número de parcelas excede o máximo permitido ("+ MAXIMO_PARCELAS_PERMITIDAS + " parcelas).");
	    }

	    return true;
	}


}
