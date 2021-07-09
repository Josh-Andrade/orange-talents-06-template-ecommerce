package br.com.orange.mercadolivre.ml.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.orange.mercadolivre.ml.domain.OpiniaoProduto;

@Repository
public interface OpiniaoProdutoRepository extends CrudRepository<OpiniaoProduto, Long> {

}
