package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import otvoreni.domain.Prvorangirani;

/**
 * Spring Data SQL repository for the Prvorangirani entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrvorangiraniRepository extends JpaRepository<Prvorangirani, Long> {}
