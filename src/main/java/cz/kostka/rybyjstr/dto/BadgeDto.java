package cz.kostka.rybyjstr.dto;

import cz.kostka.rybyjstr.enums.BadgeType;

public class BadgeDto {
    private String code;         // Unikátní kód (např. "LONGEST_FISH")
    private String title;        // Název (např. "Pán metrů")
    private String icon;         // Emoji / Ikonka (např. "📏")
    private String description;  // Popis podmínky
    private String winnerName;   // Jméno aktuálního držiteli
    private String value;        // Hodnota (např. "98 cm" nebo "12 ks")

    public BadgeDto(BadgeType type, String winnerName, String value) {
        this.code = type.getCode();
        this.title = type.getTitle();
        this.icon = type.getIcon();
        this.description = type.getDescription();
        this.winnerName = winnerName != null ? winnerName : "Zatím nikdo";
        this.value = value != null ? value : "-";
    }

    // Gettery
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public String getIcon() { return icon; }
    public String getDescription() { return description; }
    public String getWinnerName() { return winnerName; }
    public String getValue() { return value; }
}
