package org.example.sem3_dz.service;

import lombok.RequiredArgsConstructor;
import org.example.sem3_dz.model.Issue;
import org.example.sem3_dz.model.Reader;
import org.example.sem3_dz.repository.IssueRepository;
import org.example.sem3_dz.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Reader getReaderById(long id) {
        if (readerRepository.getReaderById(id) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\"");
        }
        return readerRepository.getReaderById(id);
    }

    public List<Reader> deleteReaderById(long id) {
        if (readerRepository.getReaderById(id) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\"");
        }
        return readerRepository.deleteReaderById(id);
    }

    public List<Reader> addReader(Reader book) {
        return readerRepository.addReader(book);
    }

    public List<Issue> getAllIssues(long id) {
        if (readerRepository.getReaderById(id) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\"");
        }
        return issueRepository.getAllIssues().stream().
                filter(it -> Objects.equals(it.getReaderId(), id)).
                toList();
    }

    public List<Reader> getAllReaders() {
        return readerRepository.getAllReaders();
    }
}
