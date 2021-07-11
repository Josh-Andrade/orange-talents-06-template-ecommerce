package br.com.orange.mercadolivre.ml.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.orange.mercadolivre.ml.domain.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long>{

}
