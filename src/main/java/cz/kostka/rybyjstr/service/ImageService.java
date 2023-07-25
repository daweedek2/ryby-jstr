package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.domain.Image;
import cz.kostka.rybyjstr.repository.ImageRepository;
import cz.kostka.rybyjstr.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(final ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public byte[] getCatchImage(final Long imageId) {
        return ImageUtil.decompressImage(
                imageRepository.findById(imageId).orElse(new Image()).getImageData());
    }

    public void saveImage(final Catch catchy, final MultipartFile image) throws IOException {
        imageRepository.save(new Image(ImageUtil.compressImage(image.getBytes()), catchy));
    }

    public void delete(final Long imageId) {

        imageRepository.deleteById(imageId);
    }

    public List<Image> getImagesForCatch(final Catch theCatch) {
        return imageRepository.findAllByTheCatch(theCatch);
    }
}
