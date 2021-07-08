package br.com.orange.mercadolivre.ml.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.orange.mercadolivre.ml.domain.CaracteristicaProduto;

@Repository
public interface CaracteristicaProdutoRepository extends CrudRepository<CaracteristicaProduto, Long>{

}
