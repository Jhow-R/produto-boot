package br.com.fiap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.MarcaModel;
import br.com.fiap.repository.MarcaRepository;

@RestController
@RequestMapping("/marca")
public class MarcaController {
	
	@Autowired
	public MarcaRepository repository;
	
	@GetMapping()
	public ResponseEntity<List<MarcaModel>> findAll(Model model) {

		 List<MarcaModel> marcas = repository.findAll();

		return ResponseEntity.ok(marcas);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<MarcaModel> findById(@PathVariable("id") long id, Model model) {
		
		MarcaModel marca = repository.findById(id).get();
		return ResponseEntity.ok(marca);
	}
}
