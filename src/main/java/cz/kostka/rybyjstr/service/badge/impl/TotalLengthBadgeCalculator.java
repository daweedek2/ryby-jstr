package cz.kostka.rybyjstr.service.badge.impl;

import cz.kostka.rybyjstr.dto.BadgeDto;
import cz.kostka.rybyjstr.enums.BadgeType;
import cz.kostka.rybyjstr.repository.CatchRepository;
import cz.kostka.rybyjstr.service.badge.BadgeCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TotalLengthBadgeCalculator implements BadgeCalculator {

    private final CatchRepository catchRepository;

    public TotalLengthBadgeCalculator(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    @Override
    public BadgeDto calculate() {

            List<Object[]> result = catchRepository.findTopHuntersByTotalSizeForYear();

            if (!result.isEmpty()) {
                final Object[] row = result.get(0);
                final String hunterName = (String) row[0];
                final Long totalSizeCm = (Long) row[1];

                final double meters = totalSizeCm / 100.0;

                return new BadgeDto(
                        BadgeType.TOTAL_LENGTH,
                        hunterName,
                        String.format("%.2f m celkem (%d cm)", meters, totalSizeCm)
                );
            }

            return new BadgeDto(BadgeType.TOTAL_LENGTH, null, null);
        }

}