package cz.kostka.rybyjstr.service;

import cz.kostka.rybyjstr.domain.FishType;
import cz.kostka.rybyjstr.dto.NewFishTypeDTO;
import cz.kostka.rybyjstr.repository.FishTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishTypeService {
    private final FishTypeRepository fishTypeRepository;

    @Autowired
    public FishTypeService(final FishTypeRepository fishTypeRepository) {
        this.fishTypeRepository = fishTypeRepository;
    }

    public List<FishType> getAllFishTypes() {
        return fishTypeRepository.findAll();
    }

    public FishType getFishType(final Long fishTypeId) {
        return fishTypeRepository.findById(fishTypeId).orElse(null);
    }

    public FishType getFishType(final String name) {
        return fishTypeRepository.getFishTypeByType(name);
    }

    public FishType create(final NewFishTypeDTO newFishTypeDTO) {
        return fishTypeRepository.save(new FishType(newFishTypeDTO.type()));
    }
}
