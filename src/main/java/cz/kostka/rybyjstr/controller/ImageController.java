package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(final ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/new/{catchId}")
    public String uploadImage(
            @PathVariable final Long catchId,
            @RequestParam("image") final MultipartFile image,
            final Model model)
            throws IOException {

        imageService.saveImage(catchId, image);
        return "redirect:/catch/" + catchId;
    }

    @GetMapping("/delete/{catchId}/{imageId}")
    public String deleteImage(
            @PathVariable final Long catchId,
            @PathVariable final Long imageId,
            final Model model) {

        imageService.delete(catchId, imageId);
        return "redirect:/catch/" + catchId;
    }
}
