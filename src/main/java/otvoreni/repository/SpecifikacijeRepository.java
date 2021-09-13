package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import otvoreni.domain.Specifikacije;

import java.util.List;

/**
 * Spring Data SQL repository for the Specifikacije entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecifikacijeRepository extends JpaRepository<Specifikacije, Long> {
    List<Specifikacije> findBySifraPostupka(Integer sifra_postupka);

    @Query(value = "select * from specifikacije  where specifikacije.sifra_postupka = 100000 ",nativeQuery = true)
    List<Specifikacije> allSpecifikacije();
}
