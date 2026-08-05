package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.dto.CatchDTO;
import cz.kostka.rybyjstr.service.CatchService;
import cz.kostka.rybyjstr.service.HunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotosController {

    private final CatchService catchService;
    private final HunterService hunterService;

    @Autowired
    public PhotosController(final CatchService catchService, final HunterService hunterService) {
        this.catchService = catchService;
        this.hunterService = hunterService;
    }

    @GetMapping("/{index}")
    public String viewNextPhotos(@PathVariable final int index, final Model model) {
        model.addAttribute("allCatches", catchService.getAllCatchesWithImageLatestFirst(index));
        model.addAttribute("nextPhotoIndex", catchService.getIndexForNextCatches(index));
        return "photos";
    }

    @GetMapping("")
    public String showPhotos(@RequestParam(defaultValue = "0") final int page, final Model model) {

        // Načte POUZE úlovky s fotkou v 1 rychlém SQL dotazu
        List<CatchDTO> catches = catchService.getAllCatchesOnlyWithImageLatestFirst(page);
        int nextIndex = catchService.getNextPageIndexForCatchesWithImages(page);

        model.addAttribute("catches", catches);
        model.addAttribute("allHunters", hunterService.getAllHunters());
        model.addAttribute("currentPage", page);
        model.addAttribute("nextPhotoIndex", nextIndex);

        return "photos"; // Vrací tuto šablonu
    }
}
