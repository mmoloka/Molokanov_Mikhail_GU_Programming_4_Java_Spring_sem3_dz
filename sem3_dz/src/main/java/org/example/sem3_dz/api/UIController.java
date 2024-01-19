package org.example.sem3_dz.api;
/**
 * 1. В предыдущий проект (где была библиотека с книгами, выдачами и читателями) добавить следующие рерурсы,
 * которые возвращают готовые HTML-страницы (т.е. это просто Controller'ы):
 * 1.1 /ui/books - на странице должен отобразиться список всех доступных книг в системе
 * 1.2 /ui/reader - аналогично 1.1
 * 1.3 /ui/issues - на странице отображается таблица, в которой есть столбцы (книга, читатель, когда взял, когда вернул
 * (если не вернул - пустая ячейка)).
 * 1.4* /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и перечислены книги, которые на руках
 * у этого читателя
 */

import org.example.sem3_dz.model.Book;
import org.example.sem3_dz.model.Issue;
import org.example.sem3_dz.model.Reader;
import org.example.sem3_dz.service.BookService;
import org.example.sem3_dz.service.IssueService;
import org.example.sem3_dz.service.ReaderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ui")
public class UIController {
    private final BookService bookService;
    private final ReaderService readerService;
    private final IssueService issueService;

    public UIController(BookService bookService, ReaderService readerService, IssueService issueService) {
        this.bookService = bookService;
        this.readerService = readerService;
        this.issueService = issueService;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/readers")
    public String getAllReaders(Model model) {
        List<Reader> readers = readerService.getAllReaders();
        model.addAttribute("readers", readers);
        return "readers";
    }

    @GetMapping("/issues")
    public String getAllIssues(Model model) {
        List<IssueTable> issues = new ArrayList<>();
        for (Issue issue : issueService.getAllIssues()) {
            issues.add(new IssueTable(issue, issueService));
        }
        model.addAttribute("issues", issues);
        return "issues";
    }

    @GetMapping("/reader/{id}")
    public String getAllBooksOfReader(@PathVariable long id, Model model) {
        String readerName = readerService.getReaderById(id).getName();
        List<String> books = new ArrayList<>();
        for (Issue issue : readerService.getAllIssues(id)) {
            books.add(issueService.getNameOfBook(issue));
        }
        model.addAttribute("readerName", readerName);
        model.addAttribute("books", books);
        return "readerBooks";
    }


}
