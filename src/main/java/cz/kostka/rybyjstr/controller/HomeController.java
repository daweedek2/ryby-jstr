package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.dto.NewCatchDTO;
import cz.kostka.rybyjstr.service.CatchService;
import cz.kostka.rybyjstr.service.FishTypeService;
import cz.kostka.rybyjstr.service.HunterService;
import cz.kostka.rybyjstr.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CatchService catchService;
    private final StatisticsService statisticsService;
    private final FishTypeService fishTypeService;
    private final HunterService hunterService;

    public HomeController(final CatchService catchService, final StatisticsService statisticsService,
                          final FishTypeService fishTypeService, final HunterService hunterService) {
        this.catchService = catchService;
        this.statisticsService = statisticsService;
        this.fishTypeService = fishTypeService;
        this.hunterService = hunterService;
    }

    @GetMapping
    public String getHome(final Model model) {
        addCatchModelAttributes(model);
        return "home";
    }

    private void addCatchModelAttributes(Model model) {
        model.addAttribute("newCatchDTO", NewCatchDTO.empty());
        model.addAttribute("allFishTypes", fishTypeService.getAllFishTypes());
        model.addAttribute("allHunters", hunterService.getAllHunters());
        model.addAttribute("allCatchCount", catchService.getAllCatchesCount());
        model.addAttribute("allCatchSize", catchService.getAllCatchesSize());
        model.addAttribute("topN", statisticsService.getTopN(catchService.getAllCatches(), 10));
    }

}
