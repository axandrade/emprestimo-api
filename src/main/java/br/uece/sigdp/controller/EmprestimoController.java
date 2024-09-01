package br.uece.sigdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.sigdp.entity.Emprestimo;
import br.uece.sigdp.entity.dto.EmprestimoDTO;
import br.uece.sigdp.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;

	@PostMapping("/realizar-emprestimo")
	public ResponseEntity<Emprestimo> realizarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {

		return emprestimoService.criarEmprestimo(emprestimoDTO);
	}

}
