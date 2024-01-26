package org.example.sem3_dz.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
// @Entity
@Schema(name = "Карта выдачи")
public class Issue {

    public static long sequence = 1L;
    @Schema(name = "Идентификатор")
    private final long id;
    @Schema(name = "Идентификатор книги")
    private final long bookId;
    @Schema(name = "Идентификатор читателя")
    private final long readerId;

    /**
     * Дата выдачи
     */
    @Schema(name = "Дата и время выдачи")
    private final LocalDateTime timestamp;

    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = LocalDateTime.now();
    }

}
