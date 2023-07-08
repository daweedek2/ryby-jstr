package cz.kostka.rybyjstr.repository;

import cz.kostka.rybyjstr.domain.Catch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatchRepository extends JpaRepository<Catch, Long> {
}
