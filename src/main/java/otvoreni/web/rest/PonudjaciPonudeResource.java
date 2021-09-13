package otvoreni.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import otvoreni.domain.PonudjaciPonude;
import otvoreni.repository.PonudjaciPonudeRepository;
import otvoreni.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link otvoreni.domain.PonudjaciPonude}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PonudjaciPonudeResource {

    private final Logger log = LoggerFactory.getLogger(PonudjaciPonudeResource.class);

    private final PonudjaciPonudeRepository ponudjaciPonudeRepository;

    public PonudjaciPonudeResource(PonudjaciPonudeRepository ponudjaciPonudeRepository) {
        this.ponudjaciPonudeRepository = ponudjaciPonudeRepository;
    }

    /**
     * {@code GET  /ponudjaci-ponudes} : get all the ponudjaciPonudes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ponudjaciPonudes in body.
     */
    @GetMapping("/ponudjaci-ponudes")
    public List<PonudjaciPonude> getAllPonudjaciPonudes() {
        log.debug("REST request to get all PonudjaciPonudes");
        return ponudjaciPonudeRepository.findAll();
    }

    /**
     * {@code GET  /ponudjaci-ponudes/:id} : get the "id" ponudjaciPonude.
     *
     * @param id the id of the ponudjaciPonude to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ponudjaciPonude, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ponudjaci-ponudes/{id}")
    public ResponseEntity<PonudjaciPonude> getPonudjaciPonude(@PathVariable Long id) {
        log.debug("REST request to get PonudjaciPonude : {}", id);
        Optional<PonudjaciPonude> ponudjaciPonude = ponudjaciPonudeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ponudjaciPonude);
    }
}
