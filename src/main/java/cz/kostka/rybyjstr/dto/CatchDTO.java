package cz.kostka.rybyjstr.dto;

import java.time.LocalDateTime;

public record CatchDTO(
        Long id,
        LocalDateTime timestamp,
        String fish,
        Long fishId,
        String hunter,
        Long hunterId,
        Integer size,
        Long weight,
        String note,
        Boolean isFilePresent) {

    public static CatchDTO empty() {
        return new CatchDTO(
                null, null, null, null,
                null, null, null, null,
                null, false);
    }
}
