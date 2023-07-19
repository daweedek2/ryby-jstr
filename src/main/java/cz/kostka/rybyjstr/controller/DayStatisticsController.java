package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Controller
public class DayStatisticsController {

    private final CatchService catchService;

    @Autowired
    public DayStatisticsController(final CatchService catchService) {
        this.catchService = catchService;
    }

    @GetMapping("/catch/day/{date}")
    public String getCatchesForHunter(@PathVariable final LocalDate date, final Model model) {
        model.addAttribute("allCatches", catchService.getAllCatches(date));
        model.addAttribute("date", date);
        return "dayCatches";
    }
}
