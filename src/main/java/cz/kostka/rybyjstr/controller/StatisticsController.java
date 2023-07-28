package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.dto.NewFishTypeDTO;
import cz.kostka.rybyjstr.dto.NewHunterDTO;
import cz.kostka.rybyjstr.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private static final String ALL_STATS_KEY = "allStats";
    private final StatisticsService statisticsService;

    public StatisticsController(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/hunter")
    public String getHunterStats(final Model model) {
        model.addAttribute(ALL_STATS_KEY, statisticsService.getHunterStatistics());
        model.addAttribute("newHunterDTO", NewHunterDTO.empty());
        return "hunterStatistic";
    }

    @GetMapping("/fish")
    public String getFishStats(final Model model) {
        model.addAttribute(ALL_STATS_KEY, statisticsService.getFishStatistics());
        model.addAttribute("newFishTypeDTO", NewFishTypeDTO.empty());
        return "fishStatistic";
    }

    @GetMapping("/day")
    public String getDayStats(final Model model) {
        model.addAttribute(ALL_STATS_KEY, statisticsService.getDayStatisticsTopN(3));
        model.addAttribute("hunterStats", statisticsService.getHunterStatisticsPerDay());
        model.addAttribute("fishStats", statisticsService.getFishStatisticsPerDay());
        model.addAttribute("totalCountMap", statisticsService.getTotalCountPerDay());
        return "dayStatistic";
    }

    @GetMapping("/hour")
    public String getHourStats(final Model model) {
        model.addAttribute(ALL_STATS_KEY, statisticsService.getHourStatisticsTopN(3));
        model.addAttribute("hunterStats", statisticsService.getHunterStatisticsPerHour());
        model.addAttribute("fishStats", statisticsService.getFishStatisticsPerHour());
        model.addAttribute("totalCountMap", statisticsService.getTotalCountPerHour());
        return "hourStatistic";
    }
}
