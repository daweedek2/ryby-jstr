package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.domain.Image;
import cz.kostka.rybyjstr.repository.ImageRepository;
import cz.kostka.rybyjstr.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final CatchService catchService;

    @Autowired
    public ImageService(final ImageRepository imageRepository, final CatchService catchService) {
        this.imageRepository = imageRepository;
        this.catchService = catchService;
    }

    public byte[] getCatchImage(final Long catchId) {
        return ImageUtil.decompressImage(
                imageRepository.findById(catchId).orElse(new Image()).getImageData());
    }

    public void saveImage(final Long catchId, final MultipartFile image) throws IOException {
        imageRepository.save(new Image(ImageUtil.compressImage(image.getBytes()), catchService.getCatch(catchId)));
    }

    public void delete(final Long catchId, final Long imageId) {
        catchService.removeImage(catchId, imageId);

        imageRepository.deleteById(imageId);
    }
}
