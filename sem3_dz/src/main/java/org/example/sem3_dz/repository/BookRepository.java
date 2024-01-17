package org.example.sem3_dz.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.example.sem3_dz.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

    private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
                new Book("война и мир"),
                new Book("метрвые души"),
                new Book("чистый код")
        ));
    }

    public Book getBookById(long id) {
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<Book> deleteBookById(long id) {
        books.remove(getBookById(id));
        return books;
    }

    public List<Book> addBook(Book book) {
        books.add(new Book(book.getName()));
        return books;
    }

}
