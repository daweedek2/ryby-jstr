package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import cz.kostka.rybyjstr.service.FishTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FishStatisticsController {

    private final FishTypeService fishTypeService;

    private final CatchService catchService;

    @Autowired
    public FishStatisticsController(final FishTypeService fishTypeService, final CatchService catchService) {
        this.fishTypeService = fishTypeService;
        this.catchService = catchService;
    }

    @GetMapping("/catch/fish/{id}")
    public String getCatchesForFish(@PathVariable final Long id, final Model model) {
        final var fish = fishTypeService.getFishType(id);
        model.addAttribute("allCatches", catchService.getAllCatches(fish));
        model.addAttribute("fish", fish.getType());
        return "fishCatches";
    }
}
