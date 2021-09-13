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
import otvoreni.domain.ViewPrvorangirani;
import otvoreni.repository.ViewPrvorangiraniRepository;
import otvoreni.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link otvoreni.domain.ViewPrvorangirani}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ViewPrvorangiraniResource {

    private final Logger log = LoggerFactory.getLogger(ViewPrvorangiraniResource.class);

    private final ViewPrvorangiraniRepository viewPrvorangiraniRepository;

    public ViewPrvorangiraniResource(ViewPrvorangiraniRepository viewPrvorangiraniRepository) {
        this.viewPrvorangiraniRepository = viewPrvorangiraniRepository;
    }

    /**
     * {@code GET  /view-prvorangiranis} : get all the viewPrvorangiranis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewPrvorangiranis in body.
     */
    @GetMapping("/view-prvorangiranis")
    public List<ViewPrvorangirani> getAllViewPrvorangiranis() {
        log.debug("REST request to get all ViewPrvorangiranis");
        return viewPrvorangiraniRepository.findAll();
    }

    /**
     * {@code GET  /view-prvorangiranis/:id} : get the "id" viewPrvorangirani.
     *
     * @param id the id of the viewPrvorangirani to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewPrvorangirani, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-prvorangiranis/{id}")
    public ResponseEntity<ViewPrvorangirani> getViewPrvorangirani(@PathVariable Long id) {
        log.debug("REST request to get ViewPrvorangirani : {}", id);
        Optional<ViewPrvorangirani> viewPrvorangirani = viewPrvorangiraniRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(viewPrvorangirani);
    }
}
