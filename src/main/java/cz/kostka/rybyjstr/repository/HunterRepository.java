package cz.kostka.rybyjstr.repository;

import cz.kostka.rybyjstr.domain.Hunter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HunterRepository extends JpaRepository<Hunter, Long> {
}
