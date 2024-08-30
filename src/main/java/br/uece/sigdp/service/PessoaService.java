package br.uece.sigdp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.sigdp.entity.Pessoa;
import br.uece.sigdp.repository.PessoaRepository;


@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Optional<Pessoa> findPessoaByIdentificador(String identificador) {
        return pessoaRepository.findByIdentificador(identificador);
    }

}
