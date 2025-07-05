package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.AutorService;
import br.com.alura.literalura.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {

    private final AutorService autorService;
    private final LivroService livroService;

    public ConsoleApp(AutorService autorService, LivroService livroService) {
        this.autorService = autorService;
        this.livroService = livroService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    try {
                        Livro livroSalvo = livroService.salvarLivroPorTitulo(titulo);
                        System.out.println("📚 Livro salvo com sucesso:\n" + livroSalvo);
                    } catch (Exception e) {
                        System.out.println("❌ Erro ao buscar/salvar livro: " + e.getMessage());
                    }
                    break;

                case 2:
                    List<Livro> livros = livroService.listarTodosLivros();
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro registrado.");
                    } else {
                        livros.forEach(System.out::println);
                    }
                    break;

                case 3:
                    List<Autor> autores = autorService.listarAutores();
                    if (autores.isEmpty()) {
                        System.out.println("Nenhum autor registrado.");
                    } else {
                        autores.forEach(autor -> {
                            System.out.println("\n👤 Autor: " + autor.getNome());
                            System.out.println("🎂 Ano de nascimento: " + autor.getAnoNascimento());
                            System.out.println("🪦 Ano de falecimento: " +
                                    (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "Vivo ou desconhecido"));
                            System.out.println("📚 Livros:");
                            if (autor.getLivros() == null || autor.getLivros().isEmpty()) {
                                System.out.println("  - Nenhum livro registrado para este autor.");
                            } else {
                                autor.getLivros().forEach(livro -> System.out.println("  - " + livro.getTitulo()));
                            }
                            System.out.println("---------------------------");
                        });
                    }
                    break;


                case 4:
                    System.out.print("Digite o ano para listar autores vivos: ");
                    try {
                        int ano = Integer.parseInt(scanner.nextLine());
                        var autoresVivos = autorService.buscarAutoresVivosNoAno(ano);
                        if (autoresVivos.isEmpty()) {
                            System.out.println("Nenhum autor vivo no ano " + ano);
                        } else {
                            autoresVivos.forEach(System.out::println);
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("❌ Ano inválido.");
                    }
                    break;

                case 5:
                    System.out.print("Digite o idioma (pt, en, es, fr): ");

                    String idioma = scanner.nextLine().toLowerCase();
                     List<Livro> livrosnoidioma =  livroService.buscarPorIdioma(idioma);
                     if (!livrosnoidioma.isEmpty()){
                         livrosnoidioma.forEach(System.out::println);
                     }else {
                         System.out.println(" nao a livros desse idioma no banco ");
                     }


                    break;

                case 0:
                    System.out.println("👋 Saindo...");
                    break;

                default:
                    System.out.println("❌ Opção inválida.");
            }

        } while (true);
    }

    private void exibirMenu() {
        System.out.println("\n=== MENU LITERÁLURA ===");
        System.out.println("1 - Buscar e salvar livro por título (em português)");
        System.out.println("2 - Listar livros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos em um ano");
        System.out.println("5 - Listar  de livros por idioma (pt, en, es, fr)");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }
}
