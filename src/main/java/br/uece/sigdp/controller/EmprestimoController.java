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
import br.uece.sigdp.exceptions.EmprestimoInvalidoException;
import br.uece.sigdp.service.EmprestimoService;
import br.uece.sigdp.service.PessoaService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private EmprestimoService emprestimoService;
	
	@PostMapping("/realizar")
	public ResponseEntity<String> realizarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
		Optional<Pessoa> pessoaOpt = pessoaService
				.findPessoaByIdentificador(emprestimoDTO.getPessoa().getIdentificador());

		if (pessoaOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
		}

		Pessoa pessoa = pessoaOpt.get();

		try {
			if (!pessoaService.isIdentificadorValido(pessoa.getTipoIdentificador(), pessoa.getIdentificador())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador inválido.");
			}

			pessoaService.validarLimitesEmprestimo(pessoa, emprestimoDTO.getValorEmprestimo(), emprestimoDTO.getNumeroParcelas());

			Emprestimo emprestimo = emprestimoService.criarEmprestimo(pessoa, emprestimoDTO.getValorEmprestimo(),
					emprestimoDTO.getNumeroParcelas());
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Empréstimo realizado com sucesso. ID do Empréstimo: " + emprestimo.getId());

		
		} catch (EmprestimoInvalidoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o empréstimo.");
		}
	}

}
