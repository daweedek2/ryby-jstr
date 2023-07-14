package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.dto.NewFishTypeDTO;
import cz.kostka.rybyjstr.service.FishTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fishtype")
public class FishTypeController {

    private final FishTypeService fishTypeService;

    @Autowired
    public FishTypeController(final FishTypeService fishTypeService) {
        this.fishTypeService = fishTypeService;
    }

    @PostMapping
    public String createFishType(
            final @ModelAttribute("newFishTypeDTO") NewFishTypeDTO newFishTypeDTO, final Model model) {
        final var newFish = fishTypeService.create(newFishTypeDTO);
        return "redirect:/catch/fish/" + newFish.getId();
    }
}
