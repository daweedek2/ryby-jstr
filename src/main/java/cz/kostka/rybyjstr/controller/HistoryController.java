package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.HistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(final HistoryService historyService) {
        this.historyService = historyService;
    }


    @GetMapping()
    public String showHistory(final Model model) {
        historyService.recalculate();
        model.addAttribute("historyList", historyService.findAllByOrderByYearDesc());
        return "history";
    }
}
