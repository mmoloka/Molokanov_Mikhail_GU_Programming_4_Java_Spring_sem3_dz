package org.example.sem3_dz.api;

/**
 * 1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)
 * 2.2 В сервис читателя добавить ручку GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.sem3_dz.model.Issue;
import org.example.sem3_dz.model.Reader;
import org.example.sem3_dz.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/reader")
@Tag(name = "Reader")
public class ReaderController {
    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "get reader by id", description = "Загружает читателя по индификационному номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Читатель успешно найден"),
            @ApiResponse(responseCode = "404", description = "Такого читателя нет")}
    )
    public ResponseEntity<Reader> getReaderById(@PathVariable long id) {
        final Reader reader;
        try {
            reader = readerService.getReaderById(id);
        } catch (NoSuchElementException e) {
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete reader by id", description = "Удаляет читателя по индификационному номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Читатель успешно удален"),
            @ApiResponse(responseCode = "404", description = "Такого читателя нет")}
    )
    public ResponseEntity<List<Reader>> deleteReader(@PathVariable long id) {
        final List<Reader> readers;
        try {
            readers = readerService.deleteReaderById(id);
        } catch (NoSuchElementException e) {
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "add new reader", description = "Добавляет нового читателя")
    @ApiResponse(responseCode = "201", description = "Читатель успешно создан")
    public ResponseEntity<List<Reader>> addReader(@RequestBody Reader reader) {
        log.info("Добавлен читатель:  name = {}", reader.getName());
        return new ResponseEntity<>(readerService.addReader(reader), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/issue")
    @Operation(summary = "get issues by reader id", description = "Загружает карты выдачи по индификационному номеру читателя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Карты выдачи читателя найдены"),
            @ApiResponse(responseCode = "404", description = "Такого читателя нет")}
    )
    public ResponseEntity<List<Issue>> getAllIssues(@PathVariable long id) {
        try {
            readerService.getReaderById(id);
        } catch (NoSuchElementException e) {
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(readerService.getAllIssues(id), HttpStatus.OK);
    }
}
