package cz.kostka.rybyjstr.dto;

public record NewHunterDTO(String name) {
    public static NewHunterDTO empty() {
        return new NewHunterDTO(null);
    }
}
