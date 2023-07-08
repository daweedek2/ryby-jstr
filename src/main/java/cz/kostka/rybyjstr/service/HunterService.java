package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.repository.HunterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HunterService {

    private final HunterRepository hunterRepository;

    @Autowired
    public HunterService(HunterRepository hunterRepository) {
        this.hunterRepository = hunterRepository;
    }
}
