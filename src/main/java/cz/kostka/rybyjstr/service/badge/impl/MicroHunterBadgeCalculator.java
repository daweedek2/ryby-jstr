package cz.kostka.rybyjstr.service.badge.impl;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.dto.BadgeDto;
import cz.kostka.rybyjstr.enums.BadgeType;
import cz.kostka.rybyjstr.repository.CatchRepository;
import cz.kostka.rybyjstr.service.badge.BadgeCalculator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MicroHunterBadgeCalculator implements BadgeCalculator {

    private final CatchRepository catchRepository;

    public MicroHunterBadgeCalculator(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    @Override
    public BadgeDto calculate() {
        Optional<Catch> smallestCatch = catchRepository.findFirstBySizeGreaterThanOrderBySizeAsc(0);

        if (smallestCatch.isPresent()) {
            Catch c = smallestCatch.get();
            return new BadgeDto(
                    BadgeType.MICRO_HUNTER,
                    c.getHunter().getId(),
                    c.getHunter().getName(),
                    c.getSize() + " cm (" + c.getFishType().getType() + ")"
            );
        }

        return new BadgeDto(BadgeType.MICRO_HUNTER,null, null, null);
    }
}