package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.dto.CatchDTO;
import cz.kostka.rybyjstr.dto.NewCatchDTO;
import cz.kostka.rybyjstr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class CatchController {
    private final CatchService catchService;
    private final FishTypeService fishTypeService;
    private final HunterService hunterService;
    private final StatisticsService statisticsService;
    private final ImageService imageService;

    @Autowired
    public CatchController(
            final CatchService catchService,
            final FishTypeService fishTypeService,
            final HunterService hunterService,
            final StatisticsService statisticsService,
            final ImageService imageService) {
        this.catchService = catchService;
        this.fishTypeService = fishTypeService;
        this.hunterService = hunterService;
        this.statisticsService = statisticsService;
        this.imageService = imageService;
    }

    @GetMapping
    public String getCatches(final Model model) {
        addCatchModelAttributes(model);

        return "catches";
    }

    @GetMapping("/allCatches")
    public String getAllCatches(final Model model) {
        model.addAttribute("allCatches", catchService.getAllCatchesLatestFirst());
        return "allCatches";
    }

    private void addCatchModelAttributes(Model model) {
        model.addAttribute("newCatchDTO", NewCatchDTO.empty());
        model.addAttribute("allFishTypes", fishTypeService.getAllFishTypes());
        model.addAttribute("allHunters", hunterService.getAllHunters());
        model.addAttribute("topThree", statisticsService.getTopN(catchService.getAllCatches(), 3));
    }

    @PostMapping("/catch/new")
    public String newCatch(
            final @ModelAttribute("newCatchDTO") NewCatchDTO newCatchDTO,
            final @RequestParam("image") MultipartFile image,
            final Model model) throws IOException {

        final Catch newCatch = catchService.newCatch(
                newCatchDTO,
                hunterService.getHunter(newCatchDTO.hunterId()),
                fishTypeService.getFishType(newCatchDTO.fishTypeId()));

        imageService.saveImage(newCatch, image);

        return "redirect:/catch/" + newCatch.getId();
    }

    @GetMapping("/catch/{id}")
    public String getCatchDetail(@PathVariable final Long id, final Model model) {
        model.addAttribute("catchDTO", catchService.getCatchDetailWithPhotos(id));
        return "catchDetail";
    }

    @PostMapping("/catch/{id}/update")
    public String updateCatchDetail(
            final @PathVariable Long id,
            final @ModelAttribute("newCatchDTO") CatchDTO catchDTO,
            final Model model) {
        catchService.updateCatch(catchDTO);
        return "redirect:/catch/" + id;
    }

    @GetMapping("/catch/{id}/delete")
    public String deleteCatch(@PathVariable final Long id, final Model model) {
        catchService.deleteCatch(id);
        return "redirect:/";
    }
}
