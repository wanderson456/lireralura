package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    long countByIdioma(String idioma);

    List<Livro> findByIdiomaIgnoreCase(String idioma);
    Optional<Livro> findByTituloIgnoreCase(String titulo);


}
