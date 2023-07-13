package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/hunter")
    public String getHunterStats(final Model model) {
        model.addAttribute("allStats", statisticsService.getHunterStatistics());
        return "hunterStatistic";
    }

    @GetMapping("/fish")
    public String getFishStats(final Model model) {
        model.addAttribute("allStats", statisticsService.getFishStatistics());
        return "fishStatistic";
    }
}
