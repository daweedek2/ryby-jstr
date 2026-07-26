package cz.kostka.rybyjstr.service.badge;


import cz.kostka.rybyjstr.dto.BadgeDto;

public interface BadgeCalculator {
    /**
     * Vypočítá aktuálního držitele odznaku na základě živých dat.
     */
    BadgeDto calculate();
}
