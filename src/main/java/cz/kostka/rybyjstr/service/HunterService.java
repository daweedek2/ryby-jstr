package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.domain.Hunter;
import cz.kostka.rybyjstr.repository.HunterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HunterService {

    private final HunterRepository hunterRepository;

    @Autowired
    public HunterService(HunterRepository hunterRepository) {
        this.hunterRepository = hunterRepository;
    }

    public List<Hunter> getAllHunters() {
        return hunterRepository.findAll();
    }

    public Hunter getHunter(Long hunterId) {
        return hunterRepository.findAllById(List.of(hunterId)).stream().findFirst().orElse(null);
    }
}
