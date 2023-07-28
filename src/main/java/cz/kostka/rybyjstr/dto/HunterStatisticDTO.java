package cz.kostka.rybyjstr.dto;

import java.util.List;
import java.util.Map;

public record HunterStatisticDTO(
        Long hunterId, String name, Integer totalFishCount,
        Map<String, Integer> catchesMap, Map<String, List<CatchDTO>> maxSizeCatchesMap, CatchDTO maxWeightCatch) {
}
