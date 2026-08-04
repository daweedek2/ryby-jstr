package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import cz.kostka.rybyjstr.service.HunterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatchOverviewController {

    private final CatchService catchService;
    private final HunterService hunterService;

    public CatchOverviewController(final CatchService catchService, final HunterService hunterService) {
        this.catchService = catchService;
        this.hunterService = hunterService;
    }

    @GetMapping({"/allCatches", "/photos"})
    public String showCatches(@RequestParam(defaultValue = "0") final int page, final Model model) {

        model.addAttribute("catches", catchService.getAllCatchesWithImageLatestFirst(page));
        model.addAttribute("allHunters", hunterService.getAllHunters());
        model.addAttribute("currentPage", page);
        model.addAttribute("nextPhotoIndex", catchService.getIndexForNextCatches(page));

        return "catchOverview";
    }
}
