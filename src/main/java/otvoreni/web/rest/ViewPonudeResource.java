package otvoreni.web.rest;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import otvoreni.domain.ViewPonude;
import otvoreni.repository.ViewPonudeRepository;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link otvoreni.domain.ViewPonude}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ViewPonudeResource {

    private final Logger log = LoggerFactory.getLogger(ViewPonudeResource.class);

    private final ViewPonudeRepository viewPonudeRepository;

    public ViewPonudeResource(ViewPonudeRepository viewPonudeRepository) {
        this.viewPonudeRepository = viewPonudeRepository;
    }

    @GetMapping("/view-ponude")
    public List<ViewPonude> getAllViewPonudes() {
        log.debug("REST request to get all ViewPonudes");
        return viewPonudeRepository.findAll();
    }

    @GetMapping("/view_ponude/{sifra_postupka}")
    public List<ViewPonude> getViewPonude(@PathVariable Integer sifra_postupka) {
        return viewPonudeRepository.findBySifraPostupka(sifra_postupka);
    }

    @GetMapping("/view_ponude-sifra-ponude/{sifra_ponude}")
    public List<ViewPonude> getViewSifraPonude(@PathVariable Integer sifra_ponude) {
        return viewPonudeRepository.findBySifraPonude(sifra_ponude);
    }

    @GetMapping("/view_ponudes/{id}")
    public ResponseEntity<ViewPonude> getViewPonude(@PathVariable Long id) {
        log.debug("REST request to get Ponude : {}", id);
        Optional<ViewPonude> ponude = viewPonudeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ponude);
    }
}
