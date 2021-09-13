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
import otvoreni.domain.ViewVrednovanje;
import otvoreni.repository.ViewVrednovanjeRepository;
import otvoreni.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link otvoreni.domain.ViewVrednovanje}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ViewVrednovanjeResource {

    private final Logger log = LoggerFactory.getLogger(ViewVrednovanjeResource.class);

    private final ViewVrednovanjeRepository viewVrednovanjeRepository;

    public ViewVrednovanjeResource(ViewVrednovanjeRepository viewVrednovanjeRepository) {
        this.viewVrednovanjeRepository = viewVrednovanjeRepository;
    }

    /**
     * {@code GET  /view-vrednovanjes} : get all the viewVrednovanjes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewVrednovanjes in body.
     */
    @GetMapping("/view-vrednovanjes")
    public List<ViewVrednovanje> getAllViewVrednovanjes() {
        log.debug("REST request to get all ViewVrednovanjes");
        return viewVrednovanjeRepository.findAll();
    }

    /**
     * {@code GET  /view-vrednovanjes/:id} : get the "id" viewVrednovanje.
     *
     * @param id the id of the viewVrednovanje to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewVrednovanje, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-vrednovanjes/{id}")
    public ResponseEntity<ViewVrednovanje> getViewVrednovanje(@PathVariable Long id) {
        log.debug("REST request to get ViewVrednovanje : {}", id);
        Optional<ViewVrednovanje> viewVrednovanje = viewVrednovanjeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(viewVrednovanje);
    }
}
