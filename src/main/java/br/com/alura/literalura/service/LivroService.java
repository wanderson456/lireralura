package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final GutendexClient gutendexClient;

    public LivroService(LivroRepository livroRepository,
                        AutorRepository autorRepository,
                        GutendexClient gutendexClient) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.gutendexClient = gutendexClient;
    }

    public Livro salvarLivroPorTitulo(String titulo ) {
        // tenta achar livro no banco antes
        Optional<Livro> livroExistente = livroRepository.findByTituloIgnoreCase(titulo);
        if (livroExistente.isPresent()) {
            System.out.println("Livro já existe no banco: " + titulo);
            return livroExistente.get();
        }

        LivroDTO dto = gutendexClient.buscarLivroPorTitulo(titulo);

        if (dto == null || dto.getAuthors().isEmpty()) {
            throw new RuntimeException("📕 Livro não encontrado ou sem autor disponível.");
        }

        AutorDTO autorDto = dto.getAuthors().get(0);
        Autor autor = autorRepository.findByNome(autorDto.getName())
                .orElseGet(() -> {
                    Autor novo = new Autor(
                            autorDto.getName(),
                            autorDto.getBirth_year(),
                            autorDto.getDeath_year()
                    );
                    return autorRepository.save(novo);
                });

        Livro livro = new Livro();
        livro.setTitulo(dto.getTitle());
        livro.setIdioma(dto.getLanguages().isEmpty() ? "desconhecido" : dto.getLanguages().get(0));
        livro.setDownloadCount(dto.getDownloadCount());
        livro.setAutor(autor);

        return livroRepository.save(livro);
    }


    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarPorIdioma(String idioma) {
        return livroRepository.findByIdiomaIgnoreCase(idioma);
    }
}
