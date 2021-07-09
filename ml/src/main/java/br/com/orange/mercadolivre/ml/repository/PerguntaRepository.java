package br.com.orange.mercadolivre.ml.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.orange.mercadolivre.ml.domain.Pergunta;

@Repository
public interface PerguntaRepository extends CrudRepository<Pergunta, Long>{

}
