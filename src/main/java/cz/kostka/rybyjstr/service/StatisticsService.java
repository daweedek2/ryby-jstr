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
                .toList();
    }

    private HunterStatisticDTO provideHunterStatistic(Map.Entry<String, List<CatchDTO>> entry) {
        final Hunter hunter = hunterService.getHunter(entry.getKey());
        return new HunterStatisticDTO(
                hunter.getId(),
                hunter.getName(),
                entry.getValue().size(),
                getHunterCatchesMapFromDTO(entry.getValue()),
                getTopNByFishMap(entry.getValue(), 3),
                findMaxWeightCatch(entry.getValue()));
    }

    private FishStatisticDTO provideFishStatistic(Map.Entry<String, List<CatchDTO>> entry) {
        final FishType fish = fishTypeService.getFishType(entry.getKey());
        return new FishStatisticDTO(
                fish.getId(),
                fish.getType(),
                entry.getValue().size(),
                getFishCatchesMapFromDTO(entry.getValue()),
                getTopN(entry.getValue(), 3),
                findMaxWeightCatch(entry.getValue()));
    }

    private static CatchDTO findMaxWeightCatch(final List<CatchDTO> allCatches) {
        return allCatches.stream()
                .max(Comparator.comparingLong(CatchDTO::weight))
                .orElse(CatchDTO.empty());
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
                .toList();
    }

    public List<CatchDTO> getTopN(final List<CatchDTO> catchDTOs, final long numberOfCatches) {
        return catchDTOs.stream()
                .sorted(Comparator.comparingInt(CatchDTO::size).reversed())
                .limit(numberOfCatches)
                .toList();
    }

    public Map<String, List<CatchDTO>> getTopNByFishMap(final List<CatchDTO> catchDTOs, final long topN) {
        return catchDTOs.stream()
                .collect(Collectors.groupingBy(CatchDTO::fish)) // map entries by fish
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), getTopN(entry.getValue(), topN)))
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public Map<LocalDate, Map<String, List<CatchDTO>>> getDayStatisticsTopN(final int topN) {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(c -> c.timestamp().toLocalDate()))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), getTopNByFishMap(entry.getValue(), topN)))
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public Map<LocalDate, Map<String, Integer>> getHunterStatisticsPerDay() {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(c -> c.timestamp().toLocalDate()))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), getHunterCatchesMapFromDTO(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<LocalDate, Map<String, Integer>> getFishStatisticsPerDay() {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(c -> c.timestamp().toLocalDate()))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), getFishCatchesMapFromDTO(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<LocalDate, Integer> getTotalCountPerDay() {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(c -> c.timestamp().toLocalDate()))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Map<String, List<CatchDTO>>> getHourStatisticsTopN(final int topN) {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(c -> c.timestamp().toLocalTime().getHour()))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), getTopNByFishMap(entry.getValue(), topN)))
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public Map<Integer, Map<String, Integer>> getHunterStatisticsPerHour() {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(c -> c.timestamp().toLocalTime().getHour()))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), getHunterCatchesMapFromDTO(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Map<String, Integer>> getFishStatisticsPerHour() {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(c -> c.timestamp().toLocalTime().getHour()))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), getFishCatchesMapFromDTO(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Integer> getTotalCountPerHour() {
        return catchService.getAllCatches().stream()
                .collect(Collectors.groupingBy(c -> c.timestamp().toLocalTime().getHour()))
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
