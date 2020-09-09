package br.com.fiap.business;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.CategoriaRepository;

@Component
public class ProdutoBusiness {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public ProdutoModel applyBusiness(ProdutoModel produto) {

		String novoSku = skuToUpperCase(produto.getSku());
		produto.setSku(novoSku);
		
		BigDecimal novoPreco = incrementProductValue(produto);
		produto.setPreco(novoPreco);
		
		validateProductName(produto.getNome());
		
		return produto;
	}

	private String skuToUpperCase(String sku) {
		return sku.toUpperCase();
	}

	private BigDecimal incrementProductValue(ProdutoModel produto) {
		CategoriaModel categoria = categoriaRepository.findById(produto.getCategoria().getIdCategoria()).get();
		BigDecimal preco = produto.getPreco();

		if ("Smartphone".equals(categoria.getNomeCategoria()))
			return produto.getPreco().add(new BigDecimal(10));
		else if ("Notebook".equals(categoria.getNomeCategoria()))
			return produto.getPreco().add(new BigDecimal(20));
		else
			return preco;
			
	}
	
	private void validateProductName(String name) {
		if (name.toLowerCase().contains("teste")) {
			throw new BusinessException("Nome inválido");
		}
	}
}
