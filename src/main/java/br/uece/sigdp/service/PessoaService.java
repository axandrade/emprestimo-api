package br.uece.sigdp.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.sigdp.entity.Pessoa;
import br.uece.sigdp.entity.dto.PessoaDTO;
import br.uece.sigdp.entity.enums.TipoIdentificador;
import br.uece.sigdp.exceptions.EmprestimoException;
import br.uece.sigdp.exceptions.IdentificadorInvalidoException;
import br.uece.sigdp.repository.PessoaRepository;
import br.uece.sigdp.util.ModelMapperUtil;
import br.uece.sigdp.validator.IdentificadorValidator;


@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ModelMapperUtil modelMapper;
	
    private static final int MAXIMO_PARCELAS_PERMITIDAS = 24;

	
	public Optional<Pessoa> findPessoaByIdentificador(String identificador) {
        return pessoaRepository.findByIdentificador(identificador);
    }
	
	public boolean isIdentificadorValido(TipoIdentificador tipo, String identificador) throws IdentificadorInvalidoException {
	   
	        if (tipo == TipoIdentificador.PESSOA_FISICA || tipo == TipoIdentificador.PESSOA_JURIDICA) {
	            IdentificadorValidator.validarCpfCnpj(identificador);
	        } else if (tipo == TipoIdentificador.ESTUDANTE_UNIVERSITARIO) {
	            return IdentificadorValidator.validarEstudanteUniversitario(identificador);
	        } else if (tipo == TipoIdentificador.APOSENTADO) {
	            return IdentificadorValidator.validarAposentado(identificador);
	        }
	   
	    return true;
	}
	
	public boolean validarLimitesEmprestimo(Pessoa pessoa, BigDecimal valorEmprestimo, int numeroParcelas) throws EmprestimoException {
	    try {
	        if (valorEmprestimo.compareTo(pessoa.getValorMaximoEmprestimo()) > 0) {
	            throw new EmprestimoException("Valor do empréstimo excede o valor máximo permitido para o tipo de identificador.");
	        }

	        BigDecimal valorParcela = valorEmprestimo.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP);
	        if (valorParcela.compareTo(pessoa.getValorMinimoMensalParcelas()) < 0) {
	            throw new EmprestimoException("Valor da parcela é inferior ao valor mínimo permitido para o tipo de identificador.");
	        }

	        if (numeroParcelas > MAXIMO_PARCELAS_PERMITIDAS) {
	            throw new EmprestimoException("Número de parcelas excede o máximo permitido (" + MAXIMO_PARCELAS_PERMITIDAS + " parcelas).");
	        }

	        return true;

	    } catch (Exception e) {
	        throw new EmprestimoException("Erro ao validar limites do empréstimo: " + e.getMessage(), e);
	    }
	}
	
	public Page<PessoaDTO> findByFilter(String sortDirection, String sortField, int page, int size) {

		PageRequest pageRquest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection.trim()),
				sortField.trim());

		Page<PessoaDTO> map = pessoaRepository.findAll(pageRquest).map(post -> modelMapper.map(post, PessoaDTO.class));

		return map;
	}

	@Transactional
    public void deleteByDto(PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaRepository.findById(pessoaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoaRepository.delete(pessoa);
    }
	
	

}
