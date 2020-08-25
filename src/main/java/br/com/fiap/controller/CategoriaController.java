package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	private static final String CATEGORIA_FOLDER = "categoria/";

	@Autowired
	public CategoriaRepository repository;
	
	@GetMapping("/form")
	public String open(@RequestParam String page, 
					   @RequestParam(required = false) Long id,
					   @ModelAttribute("categoriaModel") CategoriaModel categoriaModel, 
					   Model model) {
		
		if("categoria-editar".equals(page)) {
			model.addAttribute("categoriaModel", repository.findById(id).get());
		}
		
		return CATEGORIA_FOLDER + page;
	}
	
	@GetMapping()
	public ResponseEntity<List<CategoriaModel>> findAll(Model model) {

		//model.addAttribute("categorias", repository.findAll());
		 List<CategoriaModel> categorias = repository.findAll();

		return ResponseEntity.ok(categorias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> findById(@PathVariable("id") long id, Model model) {
		
		CategoriaModel categoria = repository.findById(id).get();
		return ResponseEntity.ok(categoria);
	}
	
	@PostMapping()
	public ResponseEntity save(@RequestBody @Valid CategoriaModel categoriaModel, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		
		CategoriaModel categoria = repository.save(categoriaModel);
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoria.getIdCategoria()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") long id, @Valid CategoriaModel categoriaModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return CATEGORIA_FOLDER + "categoria-editar";
		}
		
		categoriaModel.setIdCategoria(id);
		repository.save(categoriaModel);
		redirectAttributes.addFlashAttribute("messages", "Categoria alterado com sucesso!");
		
		return "redirect:/categoria";
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		
		repository.deleteById(id);
		redirectAttributes.addFlashAttribute("messages", "Categoria excluída com sucesso!");

		return "redirect:/categoria";
	}
	
}
