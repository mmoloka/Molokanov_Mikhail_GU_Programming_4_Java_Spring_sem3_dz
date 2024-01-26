package org.example.sem3_dz.api;

/**
 * 1.3 В контроллере IssueController добавить ресурс GET /issue/{id} - получить описание факта выдачи
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.sem3_dz.model.Issue;
import org.example.sem3_dz.service.IssueService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name = "Issue")
public class IssuerController {

    @Autowired
    private IssueService service;

//  @PutMapping
//  public void returnBook(long issueId) {
//    // найти в репозитории выдачу и проставить ей returned_at
//  }

    @PostMapping
    @Operation(summary = "add new issue", description = "Добавляет новую карту выдачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Карта выдачи успешно создана"),
            @ApiResponse(responseCode = "404", description = "Не найден читатель или книга"),
            @ApiResponse(responseCode = "409", description = "Читатель не вернул книгу")}
    )
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException ex) {
            log.info(ex.toString());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get issue by id", description = "Загружает карту выдачи по ее индификационному номеру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга успешно найдена"),
            @ApiResponse(responseCode = "404", description = "Такой книги нет")}
    )
    public ResponseEntity<Issue> getIssueById(@PathVariable long id) {
        final Issue issue;
        try {
            issue = service.getIssueById(id);
        } catch (NoSuchElementException e) {
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

}
