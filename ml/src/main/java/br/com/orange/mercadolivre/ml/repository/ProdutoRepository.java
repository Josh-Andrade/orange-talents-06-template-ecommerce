package br.com.orange.mercadolivre.ml.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.orange.mercadolivre.ml.domain.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{

	Optional<Produto> findByIdAndUsuario_Id(Long idProduto, Long idUsuario);
}
