package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import otvoreni.domain.Narucilac;

/**
 * Spring Data SQL repository for the Narucilac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NarucilacRepository extends JpaRepository<Narucilac, Long> {}
