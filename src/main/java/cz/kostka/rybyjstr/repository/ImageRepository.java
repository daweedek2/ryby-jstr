package cz.kostka.rybyjstr.repository;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByTheCatch(Catch theCatch);

    // Vytáhne POUZE čísla ID obrázků bez načítání obřích bajtů (image_data)
    @Query("SELECT i.id FROM Image i WHERE i.theCatch.id = :catchId")
    Set<Long> findImageIdsByCatchId(@Param("catchId") Long catchId);
}
