package cz.kostka.rybyjstr.dto;

import java.util.List;
import java.util.Map;

public record FishStatisticDTO(
        Long fishId, String name, Integer totalFishCount,
        Map<String, Integer> catchesMap, List<CatchDTO> maxSizeCatches, CatchDTO maxWeightCatch) {
}
