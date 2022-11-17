package br.com.loja.generico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja.generico.modelo.Categoria;
import br.com.loja.generico.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repository;

	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<Categoria>();
		repository.findAll().forEach(categorias::add);
		return categorias;
	}

	public Categoria obterPorId(Long id) {
		Optional<Categoria> tutorialData = repository.findById(id);
		if (tutorialData.isPresent()) {
			return tutorialData.get();
		} else {
			return null;
		}
	}
	
	public List<Categoria> obterPorNome(String nome) {
		List<Categoria> categorias = new ArrayList<Categoria>();
		repository.findByNomeContaining(nome).forEach(categorias::add);
		return categorias;

	}

	public Categoria salvar(Categoria categoria) {
		return repository.save(categoria);
	}

	public Categoria atualizar(Categoria categoria) {
		Optional<Categoria> data = repository.findById(categoria.getId());
		if (data.isPresent()) {
			return repository.save(categoria);
		} else {
			return null;
		}
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}

}
