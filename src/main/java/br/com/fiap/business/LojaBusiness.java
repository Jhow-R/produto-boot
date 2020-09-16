package br.com.fiap.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.model.LojaModel;
import br.com.fiap.repository.LojaRepository;

@Component
public class LojaBusiness {

	@Autowired
	private LojaRepository lojaRepository;

	public LojaModel applyBusiness(LojaModel loja) {

		String newUrl = urlToLowerCase(loja.getUrl());
		validateStoreName(newUrl);
		loja.setUrl(newUrl);

		return loja;
	}

	private String urlToLowerCase(String url) {
		return url.toLowerCase();
	}

	private void validateStoreName(String url) {
		if (!(url.startsWith("http://www") || url.startsWith("https://www"))) {
			throw new BusinessException("Nome inválido");
		}
	}
}
