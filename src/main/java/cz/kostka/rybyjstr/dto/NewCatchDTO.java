package cz.kostka.rybyjstr.dto;

public record NewCatchDTO(
        Long fishTypeId,
        Long hunterId,
        Integer size,
        Long weight,
        String note) {
    public static NewCatchDTO empty() {
        return new NewCatchDTO(null, null, null, null, null);
    }
}
