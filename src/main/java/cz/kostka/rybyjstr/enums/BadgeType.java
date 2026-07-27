package cz.kostka.rybyjstr.enums;

public enum BadgeType {

        LONGEST_FISH(
                "LONGEST_FISH",
                "Pán metrů",
                "📏",
                "Nejdelší ulovená ryba výpravy"
        ),
        HEAVIEST_FISH(
                "HEAVIEST_FISH",
                "Těžká váha",
                "⚖️",
                "Nejtěžší ulovená ryba výpravy"
        ),
        KING_OF_POND(
                "KING_OF_POND",
                "Pán rybníků",
                "👑",
                "Nejvyšší celkový součet bodů"
        ),
        MACHINE_GUNNER(
                "MACHINE_GUNNER",
                "Kulometčík",
                "🎣",
                "Největší počet chycených ryb"
        ),
        SNIPER(
                "SNIPER",
                "Sniper",
                "🎯",
                "Nejvyšší průměr bodů na rybu (min. 3 ks)"
        ),
        NIGHT_OWL(
                "NIGHT_OWL",
                "Noční sova",
                "🦉",
                "Nejvíce úlovků mezi 22:00 a 04:00"
        ),
        MICRO_HUNTER(
                "MICRO_HUNTER",
                "Mikro-lovec",
                "🤏",
                "Nejmenší ulovená ryba"
        ),
        TOTAL_LENGTH(
                "TOTAL_LENGTH",
                "Nekonečný metr",
                "🧵",
                "Nejvyšší celkový součet délek všech ryb"
        );

        private final String code;
        private final String title;
        private final String icon;
        private final String description;

        BadgeType(String code, String title, String icon, String description) {
            this.code = code;
            this.title = title;
            this.icon = icon;
            this.description = description;
        }

        public String getCode() { return code; }
        public String getTitle() { return title; }
        public String getIcon() { return icon; }
        public String getDescription() { return description; }
}
