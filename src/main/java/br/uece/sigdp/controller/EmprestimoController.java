package br.uece.sigdp.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.uece.sigdp.entity.Pessoa;
import br.uece.sigdp.service.PessoaService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping("/realizar")
	public ResponseEntity<String> realizarEmprestimo(@RequestParam String identificador,
			@RequestParam BigDecimal valorEmprestimo, @RequestParam int numeroParcelas) {

		Optional<Pessoa> pessoaOpt = pessoaService.findPessoaByIdentificador(identificador);
		if (pessoaOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
		}

		return null;
	}

}
