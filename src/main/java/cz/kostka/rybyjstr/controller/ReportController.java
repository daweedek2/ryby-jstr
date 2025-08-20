package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import cz.kostka.rybyjstr.service.HistoryService;
import cz.kostka.rybyjstr.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

import static cz.kostka.rybyjstr.controller.StatisticsController.ALL_FISH_STATS_GRAPH_KEY;
import static cz.kostka.rybyjstr.controller.StatisticsController.ALL_HUNTER_STATS_GRAPH_KEY;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final CatchService catchService;
    private final StatisticsService statisticsService;
    private final HistoryService historyService;

    public ReportController(
            final CatchService catchService,
            final StatisticsService statisticsService,
            final HistoryService historyService) {
        this.catchService = catchService;
        this.statisticsService = statisticsService;
        this.historyService = historyService;
    }

    @GetMapping
    public String showReport(final Model model) {
        model.addAttribute("allCatches", catchService.getAllCatchesOldestFirst()); // all catches ordered by date ASC
        model.addAttribute("allFishStats", statisticsService.getFishStatistics());
        model.addAttribute("allHunterStats", statisticsService.getHunterStatistics());
        model.addAttribute("top10Size", catchService.getAllCatchesBySize().stream().limit(10));
        model.addAttribute("top10Weight", catchService.getAllCatchesByWeight().stream().limit(10));
        model.addAttribute("historyList", List.of(historyService.getHistoryByYear(LocalDate.now().getYear())));
        addGraphData(model);

        return "report";
    }

    private void addGraphData(final Model model) {
        model.addAttribute(ALL_HUNTER_STATS_GRAPH_KEY, statisticsService.getHunterGraph());
        model.addAttribute(ALL_FISH_STATS_GRAPH_KEY, statisticsService.getFishGraph());
        model.addAttribute("totalCountDayMap", statisticsService.getTotalCountPerDayForGraph());
        model.addAttribute("totalCountHourMap", statisticsService.getTotalCountPerHour());
        model.addAttribute("totalCatchSize", catchService.getAllCatchesSize());
        model.addAttribute("carpMap", statisticsService.getFishCountPerSize("kapr"));
        model.addAttribute("jeseterMap", statisticsService.getFishCountPerSize("jeseter"));
    }
}
