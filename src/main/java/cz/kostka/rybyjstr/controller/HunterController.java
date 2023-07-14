package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.dto.NewHunterDTO;
import cz.kostka.rybyjstr.service.HunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hunter")
public class HunterController {

    private final HunterService hunterService;

    @Autowired
    public HunterController(final HunterService hunterService) {
        this.hunterService = hunterService;
    }

    @PostMapping
    public String createHunter(final @ModelAttribute("newHunterDTO") NewHunterDTO newHunterDTO, final Model model) {
        final var newHunter = hunterService.create(newHunterDTO);
        return "redirect:/catch/hunter/" + newHunter.getId();
    }
}
