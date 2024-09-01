package br.uece.sigdp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.sigdp.entity.Emprestimo;
import br.uece.sigdp.entity.Pessoa;
import br.uece.sigdp.entity.dto.EmprestimoDTO;
import br.uece.sigdp.exceptions.EmprestimoException;
import br.uece.sigdp.service.EmprestimoService;
import br.uece.sigdp.service.PessoaService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private EmprestimoService emprestimoService;
	
//http://localhost:8080/emprestimos/realizar-emprestimo
//	{
//	    "pessoa": {
//	        "id": 2,
//	        "nome": "Empresa DBZ",
//	        "identificador": "54924564000140"       
//	    },
//	    "valorEmprestimo": 80000.00,
//	    "numeroParcelas": 10
//	}
	@PostMapping("/realizar-emprestimo")
	public ResponseEntity<Emprestimo> realizarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
	    Optional<Pessoa> pessoaOpt = pessoaService
	            .findPessoaByIdentificador(emprestimoDTO.getPessoa().getIdentificador());

	    if (pessoaOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }

	    Pessoa pessoa = pessoaOpt.get();

	    try {
	        if (!pessoaService.isIdentificadorValido(pessoa.getTipoIdentificador(), pessoa.getIdentificador())) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }

	        pessoaService.validarLimitesEmprestimo(pessoa, emprestimoDTO.getValorEmprestimo(), emprestimoDTO.getNumeroParcelas());

	        Emprestimo emprestimo = emprestimoService.criarEmprestimo(pessoa, emprestimoDTO.getValorEmprestimo(),
	                emprestimoDTO.getNumeroParcelas());
	        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);

	    } catch (EmprestimoException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

}
