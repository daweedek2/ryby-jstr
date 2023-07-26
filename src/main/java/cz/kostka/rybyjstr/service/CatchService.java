package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.domain.FishType;
import cz.kostka.rybyjstr.domain.Hunter;
import cz.kostka.rybyjstr.domain.Image;
import cz.kostka.rybyjstr.dto.CatchDTO;
import cz.kostka.rybyjstr.dto.NewCatchDTO;
import cz.kostka.rybyjstr.repository.CatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CatchService {

    private final CatchRepository catchRepository;
    private final ImageService imageService;

    @Autowired
    public CatchService(final CatchRepository catchRepository, final ImageService imageService) {
        this.catchRepository = catchRepository;
        this.imageService = imageService;
    }

    public List<CatchDTO> getAllCatches() {
        return catchRepository.findAll(Sort.by("timestamp"))
                .stream()
                .map(this::mapToCatchDTOWithoutImage)
                .toList();
    }

    public List<CatchDTO> getAllCatchesWithImageLatestFirst() {
        return catchRepository.findAllByOrderByTimestampDesc()
                .stream()
                .map(this::mapToCatchDTOWithImage)
                .toList();
    }

    public List<CatchDTO> getAllCatchesLatestFirst() {
        return catchRepository.findAllByOrderByTimestampDesc()
                .stream()
                .map(this::mapToCatchDTOWithoutImage)
                .toList();
    }

    public Catch newCatch(final NewCatchDTO newCatchDTO, final Hunter hunter, final FishType fishType) {
        return catchRepository.save(
                new Catch(
                        getCatchTimestamp(newCatchDTO.time()),
                        newCatchDTO.size() == null ? 0 : newCatchDTO.size(),
                        newCatchDTO.weight() == null ? 0 : newCatchDTO.weight(),
                        newCatchDTO.note(),
                        hunter,
                        fishType));
    }

    private static LocalDateTime getCatchTimestamp(final LocalDateTime time) {
        return time != null
                ? time
                : LocalDateTime.now();
    }

    public CatchDTO getCatchDetailWithPhotos(final Long catchId) {
        return catchRepository.findById(catchId)
                .map(this::mapToCatchDTOWithImage)
                .orElse(CatchDTO.empty());
    }

    private Set<Long> getImageIds(Catch theCatch) {
        return imageService.getImagesForCatch(theCatch).stream().map(Image::getId).collect(Collectors.toSet());
    }

    public Catch getCatch(final Long catchId) {
        return catchRepository.findById(catchId).
                orElse(null);
    }

    public void deleteCatch(final Long catchId) {
        getImageIds(getCatch(catchId)).forEach(imageService::delete);
        catchRepository.deleteById(catchId);
    }

    public List<CatchDTO> getAllCatches(final Hunter hunter) {
        return getAllCatches().stream()
                .filter(catchDTO -> Objects.equals(catchDTO.hunter(), hunter.getName()))
                .toList();
    }

    public List<CatchDTO> getAllCatches(final FishType fish) {
        return getAllCatches().stream()
                .filter(catchDTO -> Objects.equals(catchDTO.fish(), fish.getType()))
                .toList();
    }

    public List<CatchDTO> getAllCatches(final LocalDate date) {
        return getAllCatches().stream()
                .filter(c -> c.timestamp().toLocalDate().isEqual(date))
                .toList();
    }

    public List<CatchDTO> getAllCatches(final Integer hour) {
        return getAllCatches().stream()
                .filter(c -> c.timestamp().toLocalTime().getHour() == hour)
                .toList();
    }

    private CatchDTO mapToCatchDTOWithoutImage(Catch catchy) {
        return new CatchDTO(
                catchy.getId(),
                catchy.getTimestamp(),
                catchy.getFishType().getType(),
                catchy.getFishType().getId(),
                catchy.getHunter().getName(),
                catchy.getHunter().getId(),
                catchy.getSize(),
                catchy.getWeight(),
                catchy.getNote(),
                Set.of());
    }

    private CatchDTO mapToCatchDTOWithImage(Catch catchy) {
        return new CatchDTO(
                catchy.getId(),
                catchy.getTimestamp(),
                catchy.getFishType().getType(),
                catchy.getFishType().getId(),
                catchy.getHunter().getName(),
                catchy.getHunter().getId(),
                catchy.getSize(),
                catchy.getWeight(),
                catchy.getNote(),
                getImageIds(catchy));
    }

    public Catch updateCatch(final CatchDTO catchDTO) {
        final Catch theCatch = getCatch(catchDTO.id());
        theCatch.setNote(catchDTO.note());
        theCatch.setSize(catchDTO.size());
        theCatch.setWeight(catchDTO.weight());
        return catchRepository.save(theCatch);
    }
}
