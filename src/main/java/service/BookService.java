package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // Métodos para criar, atualizar, deletar, listar e encontrar livros
}
