package org.example.sem3_dz.api;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

/**
 * Запрос на выдачу
 */
@Data
@Hidden
public class IssueRequest {

    /**
     * Идентификатор читателя
     */
    private long readerId;

    /**
     * Идентификатор книги
     */
    private long bookId;

}
