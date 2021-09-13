package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otvoreni.domain.UgovorPdf;

import java.util.List;

/**
 * Spring Data SQL repository for the UgovorPdf entity.
 */
@Repository
public interface UgovorPdfRepository extends JpaRepository<UgovorPdf, Long> {
    @Query("select p from UgovorPdf p where p.broj_ugovora=:broj_ugovora")
    List<UgovorPdf> findBrojUgovora(@Param("broj_ugovora") String broj_ugovora);
}
