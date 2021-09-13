package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import otvoreni.domain.HvalePonude;

/**
 * Spring Data SQL repository for the HvalePonude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HvalePonudeRepository extends JpaRepository<HvalePonude, Long> {}
