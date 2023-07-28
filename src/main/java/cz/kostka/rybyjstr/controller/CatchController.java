package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.dto.CatchDTO;
import cz.kostka.rybyjstr.dto.NewCatchDTO;
import cz.kostka.rybyjstr.service.CatchService;
import cz.kostka.rybyjstr.service.FishTypeService;
import cz.kostka.rybyjstr.service.HunterService;
import cz.kostka.rybyjstr.service.ImageService;
import cz.kostka.rybyjstr.service.StatisticsService;
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
    private final ImageService imageService;
    private final StatisticsService statisticsService;

    @Autowired
    public CatchController(
            final CatchService catchService,
            final FishTypeService fishTypeService,
            final HunterService hunterService,
            final ImageService imageService,
            final StatisticsService statisticsService) {
        this.catchService = catchService;
        this.fishTypeService = fishTypeService;
        this.hunterService = hunterService;
        this.imageService = imageService;
        this.statisticsService = statisticsService;
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

    @GetMapping("/top")
    public String getAllCatchesSorted(final Model model) {
        model.addAttribute("topN", catchService.getAllCatchesBySize());
        return "topCatch";
    }

    private void addCatchModelAttributes(Model model) {
        model.addAttribute("newCatchDTO", NewCatchDTO.empty());
        model.addAttribute("allFishTypes", fishTypeService.getAllFishTypes());
        model.addAttribute("allHunters", hunterService.getAllHunters());
        model.addAttribute("allCatchCount", catchService.getAllCatchesCount());
        model.addAttribute("topN", statisticsService.getTopN(catchService.getAllCatches(), 10));
    }

    @PostMapping("/catch/new")
    public String newCatch(
            final @ModelAttribute("newCatchDTO") NewCatchDTO newCatchDTO,
            final @RequestParam("image") MultipartFile image) throws IOException {

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
            final @ModelAttribute("newCatchDTO") CatchDTO catchDTO) {
        catchService.updateCatch(catchDTO);
        return "redirect:/catch/" + id;
    }

    @GetMapping("/catch/{id}/delete")
    public String deleteCatch(@PathVariable final Long id) {
        catchService.deleteCatch(id);
        return "redirect:/";
    }
}
