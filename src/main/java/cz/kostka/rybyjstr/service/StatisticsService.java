package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.domain.FishType;
import cz.kostka.rybyjstr.domain.Hunter;
import cz.kostka.rybyjstr.dto.CatchDTO;
import cz.kostka.rybyjstr.dto.FishStatisticDTO;
import cz.kostka.rybyjstr.dto.HunterStatisticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final CatchService catchService;
    private final HunterService hunterService;
    private final FishTypeService fishTypeService;

    @Autowired
    public StatisticsService(final CatchService catchService, final HunterService hunterService, final FishTypeService fishTypeService) {
        this.catchService = catchService;
        this.hunterService = hunterService;
        this.fishTypeService = fishTypeService;
    }

    public List<HunterStatisticDTO> getHunterStatistics() {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(CatchDTO::hunter)).entrySet().stream()
                .map(this::provideHunterStatistic)
                .sorted(Comparator.comparingInt(HunterStatisticDTO::totalFishCount).reversed())
                .collect(Collectors.toList());
    }

    private HunterStatisticDTO provideHunterStatistic(Map.Entry<String, List<CatchDTO>> entry) {
        final Hunter hunter = hunterService.getHunter(entry.getKey());
        return new HunterStatisticDTO(
                hunter.getId(),
                hunter.getName(),
                entry.getValue().size(),
                getHunterCatchesMapFromDTO(entry.getValue()),
                findMaxSizeCatch(entry.getValue()),
                findMaxWeightCatch(entry.getValue()));
    }

    private FishStatisticDTO provideFishStatistic(Map.Entry<String, List<CatchDTO>> entry) {
        final FishType fish = fishTypeService.getFishType(entry.getKey());
        return new FishStatisticDTO(
                fish.getId(),
                fish.getType(),
                entry.getValue().size(),
                getFishCatchesMapFromDTO(entry.getValue()),
                findMaxSizeCatch(entry.getValue()),
                findMaxWeightCatch(entry.getValue()));
    }

    private static CatchDTO findMaxWeightCatch(final List<CatchDTO> allCatches) {
        return allCatches.stream()
                .max(Comparator.comparingLong(CatchDTO::weight))
                .orElse(CatchDTO.empty());
    }

    private static List<CatchDTO> findMaxSizeCatch(final List<CatchDTO> allCatches) {
        return allCatches.stream()
                .sorted(Comparator.comparingInt(CatchDTO::size).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    private static Map<String, Integer> getHunterCatchesMapFromDTO(List<CatchDTO> catchDTOList) {
        return catchDTOList.stream()
                .collect(Collectors.groupingBy(CatchDTO::fish)).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
    }

    private static Map<String, Integer> getFishCatchesMapFromDTO(List<CatchDTO> catchDTOList) {
        return catchDTOList.stream()
                .collect(Collectors.groupingBy(CatchDTO::hunter)).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
    }

    public List<FishStatisticDTO> getFishStatistics() {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(CatchDTO::fish)).entrySet().stream()
                .map(this::provideFishStatistic)
                .sorted(Comparator.comparingInt(FishStatisticDTO::totalFishCount).reversed())
                .collect(Collectors.toList());
    }

    public List<CatchDTO> getTopN(final List<CatchDTO> catchDTOs, final long numberOfCatches) {
        return catchDTOs.stream()
                .sorted(Comparator.comparingInt(CatchDTO::size).reversed())
                .limit(numberOfCatches)
                .collect(Collectors.toList());
    }

    public Map<LocalDate, List<CatchDTO>> getDayStatistics() {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(c -> c.timestamp().toLocalDate()))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), getTopN(entry.getValue(), 5)))
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
