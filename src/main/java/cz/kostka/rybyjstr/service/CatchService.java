package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.domain.FishType;
import cz.kostka.rybyjstr.domain.Hunter;
import cz.kostka.rybyjstr.dto.CatchDTO;
import cz.kostka.rybyjstr.dto.NewCatchDTO;
import cz.kostka.rybyjstr.repository.CatchRepository;
import cz.kostka.rybyjstr.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatchService {

    private final CatchRepository catchRepository;

    @Autowired
    public CatchService(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    public List<CatchDTO> getAllCatches() {
        return catchRepository.findAll(Sort.by("timestamp"))
                .stream()
                .map(c -> new CatchDTO(
                        c.getId(),
                        c.getTimestamp(),
                        c.getFishType().getType(),
                        c.getHunter().getName(),
                        c.getSize(),
                        c.getWeight(),
                        c.getNote(),
                        c.getImageData() != null))
                .collect(Collectors.toList());
    }

    public void newCatch(final NewCatchDTO newCatchDTO, final Hunter hunter, final FishType fishType) {
        catchRepository.save(
                new Catch(
                        LocalDateTime.now(),
                        newCatchDTO.size() == null ? 0 : newCatchDTO.size(),
                        newCatchDTO.weight() == null ? 0 : newCatchDTO.weight(),
                        newCatchDTO.note(),
                        hunter,
                        fishType));
    }

    public CatchDTO getCatch(final Long catchId) {
        return catchRepository.findById(catchId)
                .map(c -> new CatchDTO(
                        c.getId(),
                        c.getTimestamp(),
                        c.getFishType().getType(),
                        c.getHunter().getName(),
                        c.getSize(),
                        c.getWeight(),
                        c.getNote(),
                        c.getImageData() != null))
                .orElse(CatchDTO.empty());
    }

    public byte[] getCatchImage(final Long catchId) {
        return ImageUtil.decompressImage(
                catchRepository.findById(catchId).orElse(new Catch()).getImageData());
    }

    public void saveImage(final Long catchId, final MultipartFile image) throws IOException {
        final Catch catchy = catchRepository.findById(catchId).orElse(null);

        if (catchy == null) {
            return;
        }

        catchy.setImageData(ImageUtil.compressImage(image.getBytes()));
        catchRepository.save(catchy);
    }

    public void deleteCatch(final Long catchId) {
        catchRepository.deleteById(catchId);
    }
}
