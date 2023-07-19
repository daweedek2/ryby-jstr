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
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
                        c.getFishType().getId(),
                        c.getHunter().getName(),
                        c.getHunter().getId(),
                        c.getSize(),
                        c.getWeight(),
                        c.getNote(),
                        getImageIds(c)))
                .collect(Collectors.toList());
    }

    public void newCatch(final NewCatchDTO newCatchDTO, final Hunter hunter, final FishType fishType) {
        catchRepository.save(
                new Catch(
                        getCatchTimestamp(newCatchDTO.time()),
                        newCatchDTO.size() == null ? 0 : newCatchDTO.size(),
                        newCatchDTO.weight() == null ? 0 : newCatchDTO.weight(),
                        newCatchDTO.note(),
                        hunter,
                        fishType));
    }

    private static LocalDateTime getCatchTimestamp(final LocalTime time) {
        return time != null
                ? LocalDateTime.of(LocalDate.now(), time)
                : LocalDateTime.now();
    }

    public CatchDTO getCatchDTO(final Long catchId) {
        return catchRepository.findById(catchId)
                .map(c -> new CatchDTO(
                        c.getId(),
                        c.getTimestamp(),
                        c.getFishType().getType(),
                        c.getFishType().getId(),
                        c.getHunter().getName(),
                        c.getHunter().getId(),
                        c.getSize(),
                        c.getWeight(),
                        c.getNote(),
                        getImageIds(c)))
                .orElse(CatchDTO.empty());
    }

    private static Set<Long> getImageIds(Catch c) {
        return c.getImageList().stream().map(Image::getId).collect(Collectors.toSet());
    }

    public Catch getCatch(final Long catchId) {
        return catchRepository.findById(catchId).
                orElse(null);
    }

    public void deleteCatch(final Long catchId) {
        catchRepository.deleteById(catchId);
    }

    public List<CatchDTO> getAllCatches(final Hunter hunter) {
        return getAllCatches().stream()
                .filter(catchDTO -> Objects.equals(catchDTO.hunter(), hunter.getName()))
                .collect(Collectors.toList());
    }

    public List<CatchDTO> getAllCatches(final FishType fish) {
        return getAllCatches().stream()
                .filter(catchDTO -> Objects.equals(catchDTO.fish(), fish.getType()))
                .collect(Collectors.toList());
    }

    public void attachImage(final Long catchId, final Image savedImage) {
        final var catchy = catchRepository.findById(catchId).orElse(null);

        if (catchy == null) {
            return;
        }

        catchy.getImageList().add(savedImage);
    }

    public List<CatchDTO> getAllCatches(final LocalDate date) {
        return getAllCatches().stream()
                .filter(c -> c.timestamp().toLocalDate().isEqual(date))
                .collect(Collectors.toList());
    }
}
