package br.uece.sigdp.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.uece.sigdp.entity.Emprestimo;
import br.uece.sigdp.entity.Pessoa;
import br.uece.sigdp.entity.dto.EmprestimoDTO;
import br.uece.sigdp.entity.enums.StatusPagamento;
import br.uece.sigdp.exceptions.EmprestimoException;
import br.uece.sigdp.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private PessoaService pessoaService;
	public ResponseEntity<Emprestimo> criarEmprestimo(EmprestimoDTO emprestimoDTO) {

	    Optional<Pessoa> pessoaOpt = pessoaService.findPessoaByIdentificador(emprestimoDTO.getPessoa().getIdentificador());

	    if (pessoaOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }

	    Pessoa pessoa = pessoaOpt.get();

	    try {
	        if (!pessoaService.isIdentificadorValido(pessoa.getTipoIdentificador(), pessoa.getIdentificador())) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }

	        pessoaService.validarLimitesEmprestimo(pessoa, emprestimoDTO.getValorEmprestimo(), emprestimoDTO.getNumeroParcelas());

	        Emprestimo emprestimo = saveEmprestimo(pessoa, emprestimoDTO.getValorEmprestimo(), emprestimoDTO.getNumeroParcelas());

	        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);

	    } catch (EmprestimoException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	private Emprestimo saveEmprestimo(Pessoa pessoa, BigDecimal valorEmprestimo, int numeroParcelas) {
	    try {
	        Emprestimo emprestimo = new Emprestimo(pessoa, valorEmprestimo, numeroParcelas, StatusPagamento.PENDENTE, LocalDate.now());
	        return emprestimoRepository.save(emprestimo);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao criar o empréstimo.", e); 
	    }
	}

}
