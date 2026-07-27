package cz.kostka.rybyjstr.repository;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.dto.HunterStatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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


    // odznaky
    // 1. Pán metrů (Největší size)
    Optional<Catch> findFirstByOrderBySizeDesc();

    // 2. Těžká váha (Největší weight)
    Optional<Catch> findFirstByOrderByWeightDesc();

    // 3. Pán rybníků (Nejvyšší součet bodů)
    @Query("SELECT c.hunter.name, SUM(c.points) FROM Catch c GROUP BY c.hunter.id, c.hunter.name ORDER BY SUM(c.points) DESC")
    List<Object[]> findTopHunterByTotalPoints();

    // 4. Kulometčík (Nejvyšší počet ryb)
    @Query("SELECT c.hunter.name, COUNT(c) FROM Catch c GROUP BY c.hunter.id, c.hunter.name ORDER BY COUNT(c) DESC")
    List<Object[]> findTopHunterByCatchCount();

    // 5. Sniper (Nejvyšší průměr bodů při min. 3 rybách)
    @Query("SELECT c.hunter.name, AVG(c.points) FROM Catch c GROUP BY c.hunter.id, c.hunter.name HAVING COUNT(c) >= 3 ORDER BY AVG(c.points) DESC")
    List<Object[]> findTopHunterByAvgPoints();

    // 6. Noční sova (Úlovky mezi 22:00 a 04:00)
    @Query("SELECT c.hunter.name, COUNT(c) FROM Catch c WHERE HOUR(c.timestamp) >= 22 OR HOUR(c.timestamp) < 4 GROUP BY c.hunter.id, c.hunter.name ORDER BY COUNT(c) DESC")
    List<Object[]> findTopNightOwlHunter();

    // 7. Mikro-lovec (Nejmenší size > 0)
    Optional<Catch> findFirstBySizeGreaterThanOrderBySizeAsc(int minSize);

    // Vrátí seřazený seznam lovců podle celkového součtu délek jejich ryb
    @Query("SELECT c.hunter.name, SUM(c.size) " +
            "FROM Catch c " +
            "WHERE c.size > 0 " +
            "GROUP BY c.hunter.id, c.hunter.name " +
            "ORDER BY SUM(c.size) DESC")
    List<Object[]> findTopHuntersByTotalSizeForYear();

}
