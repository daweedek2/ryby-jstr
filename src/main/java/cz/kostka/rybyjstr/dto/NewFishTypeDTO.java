package cz.kostka.rybyjstr.dto;

public record NewFishTypeDTO(String type) {
    public static NewFishTypeDTO empty() {
        return new NewFishTypeDTO(null);
    }
}
