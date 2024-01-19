package org.example.sem3_dz.service;
/**
 * 2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг. Если есть - не выдавать книгу
 * (статус ответа - 409 Conflict)
 */

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.sem3_dz.api.IssueRequest;
import org.example.sem3_dz.model.Issue;
import org.example.sem3_dz.repository.BookRepository;
import org.example.sem3_dz.repository.IssueRepository;
import org.example.sem3_dz.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IssueService {

    // спринг это все заинжектит
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Issue issue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
        if (issueRepository.getAllIssues().stream().
                filter(it -> Objects.equals(it.getReaderId(), request.getReaderId())).
                findFirst().orElse(null) != null) {
            throw new IllegalArgumentException("Читатель не вернул книгу, выдача заблокирована");
        }

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public Issue getIssueById(long id) {
        if (issueRepository.getIssueById(id) == null) {
            throw new NoSuchElementException("Не найден отчет о выдаче с идентификатором \"" + id + "\"");
        }
        return issueRepository.getIssueById(id);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }

    public String getNameOfBook(Issue issue) {
        return bookRepository.getBookById(issue.getBookId()).getName();
    }

    public String getNameOfReader(Issue issue) {
        return readerRepository.getReaderById(issue.getReaderId()).getName();
    }


}
