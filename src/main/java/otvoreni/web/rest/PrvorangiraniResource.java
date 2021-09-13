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
import otvoreni.domain.Prvorangirani;
import otvoreni.repository.PrvorangiraniRepository;
import otvoreni.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link otvoreni.domain.Prvorangirani}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PrvorangiraniResource {

    private final Logger log = LoggerFactory.getLogger(PrvorangiraniResource.class);

    private final PrvorangiraniRepository prvorangiraniRepository;

    public PrvorangiraniResource(PrvorangiraniRepository prvorangiraniRepository) {
        this.prvorangiraniRepository = prvorangiraniRepository;
    }

    /**
     * {@code GET  /prvorangiranis} : get all the prvorangiranis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prvorangiranis in body.
     */
    @GetMapping("/prvorangiranis")
    public List<Prvorangirani> getAllPrvorangiranis() {
        log.debug("REST request to get all Prvorangiranis");
        return prvorangiraniRepository.findAll();
    }

    /**
     * {@code GET  /prvorangiranis/:id} : get the "id" prvorangirani.
     *
     * @param id the id of the prvorangirani to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prvorangirani, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prvorangiranis/{id}")
    public ResponseEntity<Prvorangirani> getPrvorangirani(@PathVariable Long id) {
        log.debug("REST request to get Prvorangirani : {}", id);
        Optional<Prvorangirani> prvorangirani = prvorangiraniRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(prvorangirani);
    }
}
