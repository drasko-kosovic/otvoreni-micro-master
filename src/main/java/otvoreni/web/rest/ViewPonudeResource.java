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
import otvoreni.domain.ViewPonude;
import otvoreni.repository.ViewPonudeRepository;
import otvoreni.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
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

    /**
     * {@code GET  /view-ponudes} : get all the viewPonudes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewPonudes in body.
     */
    @GetMapping("/view-ponudes")
    public List<ViewPonude> getAllViewPonudes() {
        log.debug("REST request to get all ViewPonudes");
        return viewPonudeRepository.findAll();
    }

    /**
     * {@code GET  /view-ponudes/:id} : get the "id" viewPonude.
     *
     * @param id the id of the viewPonude to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewPonude, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-ponudes/{id}")
    public ResponseEntity<ViewPonude> getViewPonude(@PathVariable Long id) {
        log.debug("REST request to get ViewPonude : {}", id);
        Optional<ViewPonude> viewPonude = viewPonudeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(viewPonude);
    }
}
