package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.dto.NewCatchDTO;
import cz.kostka.rybyjstr.service.CatchService;
import cz.kostka.rybyjstr.service.FishTypeService;
import cz.kostka.rybyjstr.service.HunterService;
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

    @Autowired
    public CatchController(
            final CatchService catchService,
            final FishTypeService fishTypeService,
            final HunterService hunterService) {
        this.catchService = catchService;
        this.fishTypeService = fishTypeService;
        this.hunterService = hunterService;
    }

    @GetMapping
    public String getCatches(final Model model) {
        addCatchModelAttributes(model);

        return "catches";
    }

    private void addCatchModelAttributes(Model model) {
        model.addAttribute("newCatchDTO", NewCatchDTO.empty());
        model.addAttribute("allCatches", catchService.getAllCatches());
        model.addAttribute("allFishTypes", fishTypeService.getAllFishTypes());
        model.addAttribute("allHunters", hunterService.getAllHunters());
    }

    @PostMapping("/catch/new")
    public String newCatch(final @ModelAttribute("newCatchDTO") NewCatchDTO newCatchDTO, final Model model) {
        catchService.newCatch(
                newCatchDTO,
                hunterService.getHunter(newCatchDTO.hunterId()),
                fishTypeService.getFishType(newCatchDTO.fishTypeId()));
        return "redirect:/";
    }

    @GetMapping("/catch/{id}")
    public String getCatchDetail(@PathVariable final Long id, final Model model) {
        model.addAttribute("catchDTO", catchService.getCatch(id));
        return "catchDetail";
    }

    @GetMapping("/catch/{id}/delete")
    public String deleteCatch(@PathVariable final Long id, final Model model) {
        catchService.deleteCatch(id);
        return "redirect:/";
    }

    @PostMapping("/catch/{id}/image")
    public String uploadImage(@PathVariable final Long id, @RequestParam("image") MultipartFile image, final Model model)
            throws IOException {

        catchService.saveImage(id, image);
        return "redirect:/catch/" + id;
    }
}
