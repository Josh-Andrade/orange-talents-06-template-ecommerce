
package br.com.orange.mercadolivre.ml.compra;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import br.com.orange.mercadolivre.ml.controller.request.NovaCompraRequest;
import br.com.orange.mercadolivre.ml.domain.Compra;
import br.com.orange.mercadolivre.ml.domain.GatewayPagamento;
import br.com.orange.mercadolivre.ml.domain.Produto;
import br.com.orange.mercadolivre.ml.domain.Usuario;
import br.com.orange.mercadolivre.ml.repository.CompraRepository;
import br.com.orange.mercadolivre.ml.repository.ProdutoRepository;
import br.com.orange.mercadolivre.ml.repository.UsuarioRepository;

@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Profile("test")
public class CompraTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	public void naoDeveAbaterEstoque() {
		Optional<Produto> optionalProduto = produtoRepository.findById(1L);
		Produto produto = optionalProduto.get();

		assertThrows(IllegalArgumentException.class, () -> {
			produto.abaterEstoque(1);
		});
	}

	@Test
	public void deveAbaterEstoqueRegistrandoCompra() {
		NovaCompraRequest request = new NovaCompraRequest(11L, 2, GatewayPagamento.PAYPAL);

		Optional<Produto> optionalProduto = produtoRepository.findById(request.getIdProduto());
		Produto produto = optionalProduto.get();

		produto.abaterEstoque(request.getQuantidade());

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(1L);

		Compra compra = request.toEntity(produto, optionalUsuario.get());

		compraRepository.save(compra);

		assertNotNull(compra.getId());
	}
}
