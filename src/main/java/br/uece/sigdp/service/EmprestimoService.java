package br.uece.sigdp.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.sigdp.entity.Emprestimo;
import br.uece.sigdp.entity.Pessoa;
import br.uece.sigdp.entity.enums.StatusPagamento;
import br.uece.sigdp.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;

	public Emprestimo criarEmprestimo(Pessoa pessoa, BigDecimal valorEmprestimo, int numeroParcelas) {
	    try {
	        Emprestimo emprestimo = new Emprestimo(pessoa, valorEmprestimo, numeroParcelas, StatusPagamento.PENDENTE, LocalDate.now());
	        return emprestimoRepository.save(emprestimo);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao criar o empréstimo.", e); 
	    }
	}

}
