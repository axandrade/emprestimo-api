package br.uece.sigdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.sigdp.service.EmprestimoService;
import br.uece.sigdp.service.PessoaService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private EmprestimoService emprestimoService;
	
	
	
	

}
