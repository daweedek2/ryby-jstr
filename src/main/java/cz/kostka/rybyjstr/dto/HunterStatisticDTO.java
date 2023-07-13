package cz.kostka.rybyjstr.dto;

import java.util.Map;

public record HunterStatisticDTO(
        Long hunterId, String name, Integer totalFishCount,
        Map<String, Integer> catchesMap, CatchDTO maxSizeCatch, CatchDTO maxWeightCatch) {
}
