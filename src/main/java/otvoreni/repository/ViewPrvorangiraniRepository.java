package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import otvoreni.domain.ViewPrvorangirani;

/**
 * Spring Data SQL repository for the ViewPrvorangirani entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewPrvorangiraniRepository extends JpaRepository<ViewPrvorangirani, Long> {}
