package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/image")
public class ImageRestController {

    private final ImageService imageService;

    @Autowired
    public ImageRestController(final ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public void getCatchImage(@PathVariable Long id, final HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(imageService.getCatchImage(id));
        IOUtils.copy(is, response.getOutputStream());
    }
}
