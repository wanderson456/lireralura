package br.com.alura.literalura.service;



import br.com.alura.literalura.dto.GutendexResponse;
import br.com.alura.literalura.dto.LivroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GutendexClient {

    private static final String API_URL = "https://gutendex.com/books/?search=";

    public LivroDTO buscarLivroPorTitulo(String titulo) {
        try {
            var url = API_URL + titulo.replace(" ", "%20");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            GutendexResponse resposta = mapper.readValue(response.body(), GutendexResponse.class);

            if (resposta.getResults().isEmpty()) {
                throw new RuntimeException("Livro não encontrado.");
            }

            return resposta.getResults().get(0); // retorna o primeiro livro
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livro: " + e.getMessage(), e);
        }
    }
}

