package cz.kostka.rybyjstr.service.badge.impl;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.dto.BadgeDto;
import cz.kostka.rybyjstr.enums.BadgeType;
import cz.kostka.rybyjstr.repository.CatchRepository;
import cz.kostka.rybyjstr.service.badge.BadgeCalculator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HeaviestFishBadgeCalculator implements BadgeCalculator {

    private final CatchRepository catchRepository;

    public HeaviestFishBadgeCalculator(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    @Override
    public BadgeDto calculate() {
        Optional<Catch> topCatch = catchRepository.findFirstByOrderByWeightDesc();

        if (topCatch.isPresent() && topCatch.get().getWeight() > 0) {
            Catch c = topCatch.get();
            double weightKg = c.getWeight() / 1000.0; // Převod z g na kg
            return new BadgeDto(BadgeType.HEAVIEST_FISH,
                    c.getHunter().getName(),
                    String.format("%.2f kg (%s)", weightKg, c.getFishType().getType())
            );
        }

        return new BadgeDto(BadgeType.HEAVIEST_FISH, null, null);
    }
}
