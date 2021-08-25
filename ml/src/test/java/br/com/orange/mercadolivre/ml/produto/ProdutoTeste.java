
package br.com.orange.mercadolivre.ml.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import br.com.orange.mercadolivre.ml.controller.request.NovaCaracteristicaProdutoRequest;
import br.com.orange.mercadolivre.ml.controller.request.NovoProdutoRequest;
import br.com.orange.mercadolivre.ml.repository.CategoriaRepository;
import br.com.orange.mercadolivre.ml.repository.UsuarioRepository;

@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Profile("test")
public class ProdutoTeste {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	List<NovaCaracteristicaProdutoRequest> caracteristicaRequest;

	@BeforeEach
	public void init() {
		this.caracteristicaRequest = new ArrayList<>();
	}

	@Test
	public void naoDeveCriarProdutoSemTresCaracteristicasDistintas() {

		NovaCaracteristicaProdutoRequest novaCaracteristicaProdutoRequest = new NovaCaracteristicaProdutoRequest(
				"teste", "teste");

		caracteristicaRequest.add(novaCaracteristicaProdutoRequest);
		caracteristicaRequest.add(novaCaracteristicaProdutoRequest);
		caracteristicaRequest.add(novaCaracteristicaProdutoRequest);

		NovoProdutoRequest produto = new NovoProdutoRequest("Geladeira", "FrostFree", 1700.0, 10, 1L,
				caracteristicaRequest.stream().collect(Collectors.toSet()));

		assertThrows(IllegalArgumentException.class, () -> {
			produto.toEntity(categoriaRepository, usuarioRepository.findById(1L).get());
		});
	}

	@Test
	public void deveCriarProdutoComTresCaracteristicasDistintas() {

		NovaCaracteristicaProdutoRequest novaCaracteristicaProdutoRequest1 = new NovaCaracteristicaProdutoRequest(
				"teste1", "teste");
		NovaCaracteristicaProdutoRequest novaCaracteristicaProdutoRequest2 = new NovaCaracteristicaProdutoRequest(
				"teste2", "teste");
		NovaCaracteristicaProdutoRequest novaCaracteristicaProdutoRequest3 = new NovaCaracteristicaProdutoRequest(
				"teste3", "teste");

		caracteristicaRequest.add(novaCaracteristicaProdutoRequest1);
		caracteristicaRequest.add(novaCaracteristicaProdutoRequest2);
		caracteristicaRequest.add(novaCaracteristicaProdutoRequest3);

		NovoProdutoRequest produto = new NovoProdutoRequest("Geladeira", "FrostFree", 1700.0, 10, 1L,
				caracteristicaRequest.stream().collect(Collectors.toSet()));

		assertDoesNotThrow(() -> {
			produto.toEntity(categoriaRepository, usuarioRepository.findById(1L).get());
		});

	}

	@Test
	public void naoDeveCriarProdutoComMenosDeTresCaracteristicasDistintas() {

		NovaCaracteristicaProdutoRequest novaCaracteristicaProdutoRequest1 = new NovaCaracteristicaProdutoRequest(
				"teste1", "teste");
		NovaCaracteristicaProdutoRequest novaCaracteristicaProdutoRequest2 = new NovaCaracteristicaProdutoRequest(
				"teste2", "teste");

		caracteristicaRequest.add(novaCaracteristicaProdutoRequest1);
		caracteristicaRequest.add(novaCaracteristicaProdutoRequest2);

		NovoProdutoRequest produto = new NovoProdutoRequest("Geladeira", "FrostFree", 1700.0, 10, 1L,
				caracteristicaRequest.stream().collect(Collectors.toSet()));

		assertThrows(IllegalArgumentException.class, () -> {
			produto.toEntity(categoriaRepository, usuarioRepository.findById(1L).get());
		});

	}
}
