package org.example.sem3_dz.api;
/**
 * 1.1 Реализовать контроллер по управлению книгами с ручками: GET /book/{id} - получить описание книги,
 * DELETE /book/{id} - удалить книгу, POST /book - создать книгу
 */

import lombok.extern.slf4j.Slf4j;
import org.example.sem3_dz.model.Book;
import org.example.sem3_dz.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        final Book book;
        try {
            book = bookService.getBookById(id);
        } catch (NoSuchElementException e) {
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<Book>> deleteBook(@PathVariable Long id) {
        final List<Book> books;
        try {
            books = bookService.deleteBookById(id);
        } catch (NoSuchElementException e) {
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
        log.info("Добавлена книга:  name = {}", book.getName());
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }
}
