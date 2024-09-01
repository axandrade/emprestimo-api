package br.uece.sigdp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.sigdp.entity.Emprestimo;
import br.uece.sigdp.entity.Pagamento;
import br.uece.sigdp.entity.enums.StatusPagamento;
import br.uece.sigdp.exceptions.EmprestimoException;
import br.uece.sigdp.repository.EmprestimoRepository;
import jakarta.transaction.Transactional;

@Service
public class PagamentoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Transactional
	public void realizarPagamento(Long emprestimoId) throws EmprestimoException {
		Optional<Emprestimo> emprestimoOpt = emprestimoRepository.findById(emprestimoId);

		if (emprestimoOpt.isEmpty()) {
			throw new EmprestimoException("Empréstimo com ID " + emprestimoId + " não encontrado.");
		}

		Emprestimo emprestimo = emprestimoOpt.get();		
        List<Pagamento> pagamentos = emprestimo.getPagamentos();

        int proximaParcela = pagamentos.size() + 1;
        
        if (emprestimo.getStatusPagamento() == StatusPagamento.QUITADO) {
            throw new EmprestimoException("Empréstimo com ID " + emprestimoId + " já está quitado.");
        }
        
        if (proximaParcela > emprestimo.getNumeroParcelas()) {
        	emprestimo.setStatusPagamento(StatusPagamento.QUITADO);
        	emprestimoRepository.save(emprestimo); 
        	return; 
        }
		
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setDataPagamento(LocalDate.now());
        novoPagamento.setNumeroParcela(proximaParcela);
        novoPagamento.setEmprestimo(emprestimo);
        
        emprestimo.getPagamentos().add(novoPagamento);       
        
		emprestimoRepository.save(emprestimo);
	}

}
