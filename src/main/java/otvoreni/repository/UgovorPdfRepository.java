package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import otvoreni.domain.UgovorPdf;

/**
 * Spring Data SQL repository for the UgovorPdf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UgovorPdfRepository extends JpaRepository<UgovorPdf, Long> {}
