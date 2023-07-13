package cz.kostka.rybyjstr.repository;

import cz.kostka.rybyjstr.domain.FishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishTypeRepository extends JpaRepository<FishType, Long> {
    FishType getFishTypeByType(String type);
}
