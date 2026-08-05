package cz.kostka.rybyjstr.service.badge.impl;

import cz.kostka.rybyjstr.dto.BadgeDto;
import cz.kostka.rybyjstr.enums.BadgeType;
import cz.kostka.rybyjstr.repository.CatchRepository;
import cz.kostka.rybyjstr.service.badge.BadgeCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NightOwlBadgeCalculator implements BadgeCalculator {

    private final CatchRepository catchRepository;

    public NightOwlBadgeCalculator(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    @Override
    public BadgeDto calculate() {
        List<Object[]> result = catchRepository.findTopNightOwlHunter();

        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            Long hunterId = (Long) row[0];
            String hunterName = (String) row[1];
            Long nightCatches = (Long) row[2];

            return new BadgeDto(
                    BadgeType.NIGHT_OWL,
                    hunterId,
                    hunterName,
                    nightCatches + " nočních ks"
            );
        }

        return new BadgeDto(BadgeType.NIGHT_OWL,null, null, null);
    }
}