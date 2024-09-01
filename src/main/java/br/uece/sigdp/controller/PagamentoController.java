package br.uece.sigdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.uece.sigdp.exceptions.EmprestimoException;
import br.uece.sigdp.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@PostMapping("/realizar-pagamento")
    public ResponseEntity<String> realizarPagamento(@RequestParam Long emprestimoId) {
        try {
        	pagamentoService.realizarPagamento(emprestimoId);
            return ResponseEntity.status(HttpStatus.OK).body("Pagamento realizado com sucesso para o Empréstimo ID: " + emprestimoId);
        } catch (EmprestimoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o pagamento.");
        }
    }

}
