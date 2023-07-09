package cz.kostka.rybyjstr.dto;

import java.time.LocalDateTime;

public record CatchDTO(
        Long id,
        LocalDateTime timestamp,
        String fish,
        String hunter,
        Integer size,
        Long weight,
        String note) {
}
