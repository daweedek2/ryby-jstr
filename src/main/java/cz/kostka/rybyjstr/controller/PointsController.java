package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PointsController {

    private final CatchService catchService;

    public PointsController(final CatchService catchService) {
        this.catchService = catchService;
    }

    @GetMapping("/points")
    public String getPointsPage(final Model model) {
        model.addAttribute("leaderboard", catchService.getAllHunterLeaderboard());
        return "points";
    }
}
