package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.service.CatchService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/catch/{id}/image")
public class CatchImageRestController {

    private final CatchService catchService;

    @Autowired
    public CatchImageRestController(final CatchService catchService) {
        this.catchService = catchService;
    }

    @GetMapping
    public void getCatchImage(@PathVariable Long id, final HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(catchService.getCatchImage(id));
        IOUtils.copy(is, response.getOutputStream());
    }
}
