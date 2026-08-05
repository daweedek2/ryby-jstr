package cz.kostka.rybyjstr.controller;

import cz.kostka.rybyjstr.domain.FishType;
import cz.kostka.rybyjstr.domain.Hunter;
import cz.kostka.rybyjstr.repository.FishTypeRepository;
import cz.kostka.rybyjstr.repository.HunterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuickAddApiController {

    private final HunterRepository hunterRepository;
    private final FishTypeRepository fishTypeRepository;

    public QuickAddApiController(HunterRepository hunterRepository, FishTypeRepository fishTypeRepository) {
        this.hunterRepository = hunterRepository;
        this.fishTypeRepository = fishTypeRepository;
    }

    @PostMapping("/hunters")
    public ResponseEntity<Hunter> addHunter(@RequestBody Hunter hunter) {
        Hunter saved = hunterRepository.save(hunter);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/fish-types")
    public ResponseEntity<FishType> addFishType(@RequestBody FishType fishType) {
        FishType saved = fishTypeRepository.save(fishType);
        return ResponseEntity.ok(saved);
    }
}
