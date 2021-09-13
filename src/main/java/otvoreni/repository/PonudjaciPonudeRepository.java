package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import otvoreni.domain.PonudjaciPonude;

/**
 * Spring Data SQL repository for the PonudjaciPonude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PonudjaciPonudeRepository extends JpaRepository<PonudjaciPonude, Long> {}
