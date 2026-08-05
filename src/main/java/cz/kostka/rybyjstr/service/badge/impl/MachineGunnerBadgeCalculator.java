package cz.kostka.rybyjstr.service.badge.impl;

import cz.kostka.rybyjstr.dto.BadgeDto;
import cz.kostka.rybyjstr.enums.BadgeType;
import cz.kostka.rybyjstr.repository.CatchRepository;
import cz.kostka.rybyjstr.service.badge.BadgeCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MachineGunnerBadgeCalculator implements BadgeCalculator {

    private final CatchRepository catchRepository;

    public MachineGunnerBadgeCalculator(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    @Override
    public BadgeDto calculate() {
        List<Object[]> result = catchRepository.findTopHunterByCatchCount();

        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            Long hunterId = (Long) row[0];
            String hunterName = (String) row[1];
            Long count = (Long) row[2];

            return new BadgeDto(
                    BadgeType.MACHINE_GUNNER,
                    hunterId,
                    hunterName,
                    count + " ks"
            );
        }

        return new BadgeDto(BadgeType.MACHINE_GUNNER, null, null, null);
    }
}