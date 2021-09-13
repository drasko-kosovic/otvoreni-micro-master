package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import otvoreni.domain.Anex;

/**
 * Spring Data SQL repository for the Anex entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnexRepository extends JpaRepository<Anex, Long> {}
