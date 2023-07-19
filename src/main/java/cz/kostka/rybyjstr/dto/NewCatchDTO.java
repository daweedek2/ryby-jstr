package cz.kostka.rybyjstr.dto;

import java.time.LocalTime;

public record NewCatchDTO(
        Long fishTypeId,
        Long hunterId,
        Integer size,
        Long weight,
        LocalTime time,
        String note) {
    public static NewCatchDTO empty() {
        return new NewCatchDTO(null, null, null, null, null, null);
    }
}
