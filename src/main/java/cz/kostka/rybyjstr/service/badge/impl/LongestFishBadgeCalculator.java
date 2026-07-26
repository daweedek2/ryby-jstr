package cz.kostka.rybyjstr.service.badge.impl;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.dto.BadgeDto;
import cz.kostka.rybyjstr.enums.BadgeType;
import cz.kostka.rybyjstr.repository.CatchRepository;
import cz.kostka.rybyjstr.service.badge.BadgeCalculator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LongestFishBadgeCalculator implements BadgeCalculator {

    private final CatchRepository catchRepository;

    public LongestFishBadgeCalculator(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    @Override
    public BadgeDto calculate() {
        Optional<Catch> topCatch = catchRepository.findFirstByOrderBySizeDesc();

        if (topCatch.isPresent() && topCatch.get().getSize() > 0) {
            Catch c = topCatch.get();
            return new BadgeDto(BadgeType.LONGEST_FISH,
                    c.getHunter().getName(),
                    c.getSize() + " cm (" + c.getFishType().getType() + ")"
            );
        }

        return new BadgeDto(BadgeType.LONGEST_FISH, null, null);
    }
}