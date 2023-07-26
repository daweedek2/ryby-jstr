package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HourStatisticsController {

    private final CatchService catchService;

    @Autowired
    public HourStatisticsController(final CatchService catchService) {
        this.catchService = catchService;
    }

    @GetMapping("/catch/hour/{hour}")
    public String getCatchesForHunter(@PathVariable final Integer hour, final Model model) {
        model.addAttribute("allCatches", catchService.getAllCatches(hour));
        model.addAttribute("hour", hour);
        return "hourCatches";
    }
}
