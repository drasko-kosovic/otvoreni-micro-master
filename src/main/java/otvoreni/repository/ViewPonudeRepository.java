package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import otvoreni.domain.ViewPonude;

/**
 * Spring Data SQL repository for the ViewPonude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewPonudeRepository extends JpaRepository<ViewPonude, Long> {}
