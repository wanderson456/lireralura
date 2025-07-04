package br.com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer download_count;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Livro() {}

    public Livro(String titulo, String idioma, Integer download_count, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.download_count = download_count;
        this.autor = autor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Integer getdownload_count() { return download_count; }
    public void setdownload_count(Integer download_count) { this.download_count = download_count; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro)) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "📘 Título: " + titulo +
                "\n🌐 Idioma: " + idioma +
                "\n⬇️ Downloads: " + download_count +
                "\n" + autor +
                "\n--------------------------";
    }
}
