package org.example.sem3_dz.service;

import lombok.RequiredArgsConstructor;
import org.example.sem3_dz.model.Book;
import org.example.sem3_dz.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getBookById(long id) {
        if (bookRepository.getBookById(id) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + id + "\"");
        }
        return bookRepository.getBookById(id);
    }

    public List<Book> deleteBookById(long id) {
        if (bookRepository.getBookById(id) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + id + "\"");
        }
        return bookRepository.deleteBookById(id);
    }

    public List<Book> addBook(Book book) {
        return bookRepository.addBook(book);
    }
}
