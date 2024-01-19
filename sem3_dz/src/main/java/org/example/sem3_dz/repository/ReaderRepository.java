package org.example.sem3_dz.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.example.sem3_dz.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

    private final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        readers.addAll(List.of(
                new Reader("Игорь"),
                new Reader("Иван"),
                new Reader("Петр"),
                new Reader("Ольга")
        ));
    }

    public Reader getReaderById(long id) {
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<Reader> deleteReaderById(long id) {
        readers.remove(getReaderById(id));
        return readers;
    }

    public List<Reader> addReader(Reader reader) {
        readers.add(new Reader(reader.getName()));
        return readers;
    }

    public List<Reader> getAllReaders() {
        return readers;
    }

}
