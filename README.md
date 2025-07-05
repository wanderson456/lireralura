# 📚 Literalura

Aplicação em Java com Spring Boot que consome a API pública [Gutendex](https://gutendex.com/) para buscar livros por título, armazenar suas informações em um banco de dados PostgreSQL e permitir consultas via console interativo.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.5+
- Spring Data JPA
- PostgreSQL
- Hibernate
- API Gutendex (https://gutendex.com)


---

## 🎯 Funcionalidades

A aplicação permite:

- 🔍 Buscar um livro por título (em português) e armazenar no banco
- 📃 Listar todos os livros registrados
- 🧑‍💻 Listar todos os autores registrados
- 📆 Listar autores vivos em um determinado ano
- 🌐 Listar livros por idioma (`pt`, `en`, `es`, `fr`)
- Evitar duplicação ao salvar um mesmo livro
- Exibir os livros associados a um autor

---

## 🗃️ Estrutura do Banco de Dados

- **Livro**:
  - `id`
  - `titulo`
  - `idioma`
  - `download_count`
  - `autor_id` (relacionamento ManyToOne)

- **Autor**:
  - `id`
  - `nome`
  - `anoNascimento`
  - `anoFalecimento`
  - `livros` (relacionamento OneToMany)

---

## ⚙️ Como Executar

1. Clone o projeto:
   ```bash
   git clone https://github.com/seu-usuario/literalura.git
   cd literalura
  
2. Configure o banco de dados PostgreSQL no arquivo application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
 ```bash
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

🧪 Exemplo de Uso
Menu principal exibido no console:
 ```bash
=== MENU LITERÁLURA ===
1 - Buscar e salvar livro por título 
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um ano
5 - Listar de livros por idioma (pt, en, es, fr)
0 - Sair
 ```
Exemplo de saída ao salvar um livro:
 ```bash
📚 Livro salvo com sucesso:
📘 Título: Dom Casmurro
🌐 Idioma: pt
⬇️ Downloads: 2598
👤 Autor: Machado de Assis
📅 Nascimento: 1839
💀 Falecimento: 1908
--------------------------
```
📌 Observações
A API do Gutendex pode retornar múltiplos resultados para um título — a aplicação pega apenas o primeiro.

O relacionamento entre livro e autor é validado antes de salvar, para evitar duplicatas.
