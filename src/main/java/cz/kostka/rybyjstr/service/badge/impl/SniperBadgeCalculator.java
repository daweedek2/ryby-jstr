package cz.kostka.rybyjstr.service.badge.impl;

import cz.kostka.rybyjstr.dto.BadgeDto;
import cz.kostka.rybyjstr.enums.BadgeType;
import cz.kostka.rybyjstr.repository.CatchRepository;
import cz.kostka.rybyjstr.service.badge.BadgeCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SniperBadgeCalculator implements BadgeCalculator {

    private final CatchRepository catchRepository;

    public SniperBadgeCalculator(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    @Override
    public BadgeDto calculate() {
        List<Object[]> result = catchRepository.findTopHunterByAvgPoints();

        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            String hunterName = (String) row[0];
            Double avgPoints = (Double) row[1];
            double roundedAvg = Math.round(avgPoints * 10.0) / 10.0;

            return new BadgeDto(
                    BadgeType.SNIPER,
                    hunterName,
                    roundedAvg + " bodů/ryba"
            );
        }

        return new BadgeDto(BadgeType.SNIPER, null, null);
    }
}
