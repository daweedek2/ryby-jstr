package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photos")
public class PhotosController {

    private final CatchService catchService;

    @Autowired
    public PhotosController(final CatchService catchService) {
        this.catchService = catchService;
    }

    @GetMapping
    public String viewAllPhotos(final Model model) {
        model.addAttribute("allCatches", catchService.getAllCatchesWithImageLatestFirst());
        return "photos";
    }
}
