package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.domain.History;
import cz.kostka.rybyjstr.dto.FishStatisticDTO;
import cz.kostka.rybyjstr.dto.HunterStatisticDTO;
import cz.kostka.rybyjstr.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final StatisticsService statisticsService;
    private final CatchService catchService;

    public HistoryService(final HistoryRepository historyRepository,
                          final StatisticsService statisticsService,
                          final CatchService catchService) {
        this.historyRepository = historyRepository;
        this.statisticsService = statisticsService;
        this.catchService = catchService;
    }

    public List<History> findAllByOrderByYearDesc() {
        return historyRepository.findAllByOrderByYearDesc();
    }

    public History recalculate() {
        final int currentYear = LocalDate.now().getYear();
        final History history = getHistoryByYear(currentYear);

        final var fishStats = statisticsService.getFishStatistics();
        final var hunterStats = statisticsService.getHunterStatistics();

        final int totalFishCount = fishStats.stream()
                .mapToInt(FishStatisticDTO::totalFishCount)
                .sum();

        final String fishTypeNames = fishStats.stream()
                        .map(FishStatisticDTO::name)
                                .collect(Collectors.joining(" "));

        final String hunterNames = hunterStats.stream()
                .map(HunterStatisticDTO::name)
                .collect(Collectors.joining(" "));

        history.setYear(currentYear);
        history.setTotalFishCount(totalFishCount);
        history.setTotalFishSize(catchService.getAllCatchesSize());
        history.setFishTypeNames(fishTypeNames);
        history.setHunterNames(hunterNames);

        return historyRepository.save(history);
    }

    public History getHistoryByYear(final int currentYear) {
        final History history =  historyRepository.getHistoryByYear(currentYear);

        if (history != null) {
            return history;
        }

        return new History();
    }
}
