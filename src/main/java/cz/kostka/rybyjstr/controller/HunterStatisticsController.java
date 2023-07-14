package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import cz.kostka.rybyjstr.service.HunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HunterStatisticsController {

    private final HunterService hunterService;

    private final CatchService catchService;

    @Autowired
    public HunterStatisticsController(final HunterService hunterService, final CatchService catchService) {
        this.hunterService = hunterService;
        this.catchService = catchService;
    }

    @GetMapping("/catch/hunter/{id}")
    public String getCatchesForHunter(@PathVariable final Long id, final Model model) {
        final var hunter = hunterService.getHunter(id);
        model.addAttribute("allCatches", catchService.getAllCatches(hunter));
        model.addAttribute("hunter", hunter.getName());
        return "hunterCatches";
    }
}
