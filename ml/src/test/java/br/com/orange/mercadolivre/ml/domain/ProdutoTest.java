
package br.com.orange.mercadolivre.ml.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import br.com.orange.mercadolivre.ml.controller.request.NovaCaracteristicaProdutoRequest;
import br.com.orange.mercadolivre.ml.controller.request.NovoProdutoRequest;
import br.com.orange.mercadolivre.ml.repository.CategoriaRepository;
import br.com.orange.mercadolivre.ml.repository.ProdutoRepository;
import br.com.orange.mercadolivre.ml.repository.UsuarioRepository;


@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
@Transactional
public class ProdutoTest {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	private Set<NovaCaracteristicaProdutoRequest> caracteristicaRequest;
	private Usuario usuario;
	private Categoria categoria;
	private Produto produto;
	@BeforeEach
	public void setup() {
		caracteristicaRequest = new HashSet<NovaCaracteristicaProdutoRequest>();
		caracteristicaRequest.add(new NovaCaracteristicaProdutoRequest("teste", "teste1"));
		caracteristicaRequest.add(new NovaCaracteristicaProdutoRequest("teste", "teste2"));
		caracteristicaRequest.add(new NovaCaracteristicaProdutoRequest("teste", "teste3"));

		usuario = usuarioRepository.save(new Usuario("teste@test.com", 
				new SenhaSemHash("123456")));
		categoria = categoriaRepository.save(new Categoria("Eletrodomestico"));
		produto = produtoRepository.save(new Produto(
				"Geladeira", 
				"FrostFree", 
				1000.0, 
				100,
				categoria, 
				caracteristicaRequest,usuario));
	}
	
	@Test
	public void naoDeveAbaterEstoque() {
		
		assertEquals(1, produtoRepository.count());
		Optional<Produto> optionalProduto = produtoRepository.findById(produto.getId());
		Produto produtoSalvo = optionalProduto.get();

		assertThrows(IllegalArgumentException.class, () -> {
			produtoSalvo.abaterEstoque(101);
		});
	}

	@Test
	public void deveAbaterEstoque() {
		assertEquals(1, produtoRepository.count());
		
		Optional<Produto> optionalProduto = produtoRepository.findById(produto.getId());
		Produto produto = optionalProduto.get();

		assertDoesNotThrow(() -> {
			produto.abaterEstoque(5);
		});

	}
	
	@Test
	public void deveRetornarEntidadeProdutoComTresCaracteristicasDistintas() {
		NovoProdutoRequest produto = new NovoProdutoRequest("Geladeira", "FrostFree", 1700.0, 10, categoria.getId(),
				caracteristicaRequest);

		assertDoesNotThrow(() -> {
			produto.toEntity(categoriaRepository, usuarioRepository.findById(usuario.getId()).get());
		});
	}
	

	@Test
	public void naoDeveRetornarEntidadeProdutoSemTresCaracteristicasDistintas() {

		caracteristicaRequest.removeAll(caracteristicaRequest);
		NovoProdutoRequest produto = new NovoProdutoRequest("Geladeira", "FrostFree", 1700.0, 10, categoria.getId(),
				caracteristicaRequest);

		assertThrows(IllegalArgumentException.class, () -> {
			produto.toEntity(categoriaRepository, usuarioRepository.findById(usuario.getId()).get());
		});
	}
}
