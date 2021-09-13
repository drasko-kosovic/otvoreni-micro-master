package otvoreni.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otvoreni.domain.ViewPrvorangirani;

import java.util.List;

/**
 * Spring Data SQL repository for the ViewPrvorangirani entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewPrvorangiraniRepository extends JpaRepository<ViewPrvorangirani, Long> {

    //    @Query("select p from Prvorangirani p where p.sifraPostupka=:sifraPostupka")
    List<ViewPrvorangirani> findBySifraPostupka(@Param("sifraPostupka") Integer sifra);

    @Query("select p from Prvorangirani p where p.sifraPonude=:sifraPonude")
    List<ViewPrvorangirani> findBySifraPonude(@Param("sifraPonude") Integer sifra);


    List<ViewPrvorangirani> findBySifraPostupkaAndSifraPonude(@Param("sifraPostupka") Integer sifraPostupka, @Param("sifraPonude") Integer sifraPonude);


}
