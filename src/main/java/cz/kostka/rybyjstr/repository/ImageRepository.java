package cz.kostka.rybyjstr.repository;

import cz.kostka.rybyjstr.domain.Catch;
import cz.kostka.rybyjstr.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByTheCatch(Catch theCatch);

    // Vytáhne dvojice [catchId, imageId] pro celý seznam úlovků naráz v 1 SQL dotazu!
    @Query("SELECT i.theCatch.id, i.id FROM Image i WHERE i.theCatch.id IN :catchIds")
    List<Object[]> findImageIdsByCatchIds(@Param("catchIds") List<Long> catchIds);
}
