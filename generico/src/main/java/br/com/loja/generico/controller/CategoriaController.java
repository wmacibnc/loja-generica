package br.com.loja.generico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.generico.modelo.Categoria;
import br.com.loja.generico.service.CategoriaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({ "/categoria" })
public class CategoriaController {

	@Autowired
	CategoriaService service;

	@GetMapping("/listar")
	public ResponseEntity<List<Categoria>> listar() {
		try {
			List<Categoria> categorias = service.listar();
			if (categorias.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(categorias, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obterPorId(@PathVariable("id") Long id) {
		Categoria categoria = service.obterPorId(id);
		if (categoria != null) {
			return new ResponseEntity<>(categoria, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/consultar/{nome}")
	public ResponseEntity<List<Categoria>> obterPorNome(@PathVariable("nome") String nome) {
		try {
			List<Categoria> categorias = service.obterPorNome(nome);
			if (categorias.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(categorias, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
		try {
			return new ResponseEntity<>(service.salvar(categoria), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Categoria> atualizar(@RequestBody Categoria categoria) {
		Categoria data = service.atualizar(categoria);
		if (data != null) {
			return new ResponseEntity<>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/remover/{id}")
	public ResponseEntity<HttpStatus> remover(@PathVariable("id") Long id) {
		try {
			service.remover(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
