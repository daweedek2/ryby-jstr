package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.repository.FishTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishTypeService {
    private final FishTypeRepository fishTypeRepository;

    @Autowired
    public FishTypeService(FishTypeRepository fishTypeRepository) {
        this.fishTypeRepository = fishTypeRepository;
    }
}
