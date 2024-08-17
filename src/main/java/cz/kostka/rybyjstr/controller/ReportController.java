package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import cz.kostka.rybyjstr.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final CatchService catchService;
    private final StatisticsService statisticsService;

    public ReportController(final CatchService catchService, final StatisticsService statisticsService) {
        this.catchService = catchService;
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public String showReport(final Model model) {
        model.addAttribute("allCatches", catchService.getAllCatchesOldestFirst()); // all catches ordered by date ASC
        model.addAttribute("allFishStats", statisticsService.getFishStatistics());
        model.addAttribute("allHunterStats", statisticsService.getHunterStatistics());
        model.addAttribute("top10Size", catchService.getAllCatchesBySize().stream().limit(10));
        model.addAttribute("top10Weight", catchService.getAllCatchesByWeight().stream().limit(10));
        return "report";
    }
}
