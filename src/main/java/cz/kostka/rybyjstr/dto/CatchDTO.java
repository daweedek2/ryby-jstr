package cz.kostka.rybyjstr.dto;

import java.time.LocalDateTime;
import java.util.Set;

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
        Set<Long> imageIds) {

    public static CatchDTO empty() {
        return new CatchDTO(
                null, null, null, null,
                null, null, null, null, null, Set.of());
    }
}
