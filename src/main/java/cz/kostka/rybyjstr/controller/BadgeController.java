package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.badge.BadgeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BadgeController {

    private final BadgeService  badgeService;

    public BadgeController(final BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping("/badges")
    public String showBadges(final Model model) {
        model.addAttribute("badges", badgeService.getAllLiveBadges());
        return "badges";
    }
}
