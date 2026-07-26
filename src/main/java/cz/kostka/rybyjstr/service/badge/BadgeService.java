package cz.kostka.rybyjstr.service.badge;

import cz.kostka.rybyjstr.dto.BadgeDto;
import cz.kostka.rybyjstr.service.badge.BadgeCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    private final List<BadgeCalculator> badgeCalculators;

    // Spring automaticky injektuje VŠECHNY implementace rozhraní BadgeCalculator
    public BadgeService(List<BadgeCalculator> badgeCalculators) {
        this.badgeCalculators = badgeCalculators;
    }

    /**
     * Vypočítá živý seznam všech odznaků v aplikaci.
     */
    public List<BadgeDto> getAllLiveBadges() {
        return badgeCalculators.stream()
                .map(BadgeCalculator::calculate)
                .toList();
    }
}
