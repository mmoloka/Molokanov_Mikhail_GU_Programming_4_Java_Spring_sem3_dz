package org.example.sem3_dz.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Schema(name = "Читатель")
public class Reader {

    public static long sequence = 1L;
    @Schema(name = "Идентификатор")
    private final long id;
    @Schema(name = "Имя")
    private final String name;

    public Reader(String name) {
        this(sequence++, name);
    }

}
