package br.com.alura.literalura.service;





import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> buscarAutoresVivosNoAno(Integer ano) {
        if (ano == null || ano < 0) {
            throw new IllegalArgumentException("Ano inválido!");
        }
        return autorRepository.findAutoresVivosNoAno(ano);
    }

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }
}
