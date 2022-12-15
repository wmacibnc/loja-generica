package br.com.loja.generico;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.loja.generico.modelo.Categoria;
import br.com.loja.generico.repository.CategoriaRepository;

@SpringBootApplication
public class GenericoApplication {

	private static Integer i = 1;

	public static void main(String[] args) {
		SpringApplication.run(GenericoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CategoriaRepository repository) {
		return args -> {
			Stream.of("Boneca", "Presentes", "Kits", "Diversos", "Canetas").forEach(name -> {
				Categoria categoria = new Categoria();
				categoria.setNome(name);
				categoria.setDescricao("Descrição " + String.valueOf(i++));
				repository.save(categoria);
			});
			repository.findAll().forEach(System.out::println);
		};
	}

}
