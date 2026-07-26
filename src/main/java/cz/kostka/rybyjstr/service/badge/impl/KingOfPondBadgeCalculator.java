package cz.kostka.rybyjstr.service.badge.impl;

import cz.kostka.rybyjstr.dto.BadgeDto;
import cz.kostka.rybyjstr.enums.BadgeType;
import cz.kostka.rybyjstr.repository.CatchRepository;
import cz.kostka.rybyjstr.service.badge.BadgeCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KingOfPondBadgeCalculator implements BadgeCalculator {

    private final CatchRepository catchRepository;

    public KingOfPondBadgeCalculator(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    @Override
    public BadgeDto calculate() {
        List<Object[]> result = catchRepository.findTopHunterByTotalPoints();

        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            String hunterName = (String) row[0];
            Long totalPoints = (Long) row[1];

            return new BadgeDto(
                    BadgeType.KING_OF_POND,
                    hunterName,
                    totalPoints + " bodů"
            );
        }

        return new BadgeDto(BadgeType.KING_OF_POND, null, null);
    }
}