package org.example.sem3_dz.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Schema(name = "Книга")
public class Book {

    public static long sequence = 1L;
    @Schema(name = "Идентификатор")
    private final long id;
    @Schema(name = "Название")
    private final String name;

    public Book(String name) {
        this(sequence++, name);
    }

}
