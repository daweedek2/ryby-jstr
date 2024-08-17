package cz.kostka.rybyjstr.dto;

import java.time.LocalDateTime;

public record CatchViewDTO(Long id, LocalDateTime timestamp, String hunter, String fish, String size, String weight) {
}
