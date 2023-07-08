package cz.kostka.rybyjstr.dto;

public record CatchDTO(
        String fish,
        String hunter,
        Integer size,
        Long weight,
        String note) {
}
