package cz.kostka.rybyjstr.repository;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.dto.HunterStatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatchRepository extends JpaRepository<Catch, Long> {
    List<Catch> findAllByOrderByTimestampDesc();
    List<Catch> findAllByOrderByTimestampAsc();
    List<Catch> findAllByOrderBySizeDesc();
    List<Catch> findAllByOrderByWeightDesc();
    @Query("SELECT SUM(c.size) FROM Catch c")
    Integer sumAllSizes();

    @Query("SELECT new cz.kostka.rybyjstr.dto.HunterStatsDto(" +
            "c.hunter.name, SUM(c.points), AVG(c.points), COUNT(c), MAX(c.size)) " +
            "FROM Catch c " +
            "GROUP BY c.hunter.id, c.hunter.name " +
            "ORDER BY SUM(c.points) DESC")
    List<HunterStatsDto> getFullLeaderboard();


}
