package br.uece.sigdp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.sigdp.entity.Pessoa;

@Repository
public interface PagamentoRepository extends JpaRepository<Pessoa, Long>{
	Optional<Pessoa> findByIdentificador(String identificador);
}
