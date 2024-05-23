package controller;

import model.Emprestimo;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaController {
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;

    public BibliotecaController() {
        this.livros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void addBook(Livro livro) {
        this.livros.add(livro);
    }

    public void removeBookByIsbn(String isbn) {
        livros.removeIf(book -> book.getIsbn().equals(isbn));
    }
}