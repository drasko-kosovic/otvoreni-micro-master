package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otvoreni.domain.ViewVrednovanje;

import java.util.List;

/**
 * Spring Data SQL repository for the ViewVrednovanje entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewVrednovanjeRepository extends JpaRepository<ViewVrednovanje, Long> {
    @Query("select v from ViewVrednovanje v where v.sifraPostupka=:sifraPostupka ")
    List<ViewVrednovanje> findBySifraPotupka(@Param("sifraPostupka") Integer sifraPostupka);
    List<ViewVrednovanje> findBySifraPonude(@Param("sifraPonude") Integer sifraPonude);
}
