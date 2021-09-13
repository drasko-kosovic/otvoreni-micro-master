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
import otvoreni.domain.Narucilac;
import otvoreni.repository.NarucilacRepository;
import otvoreni.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link otvoreni.domain.Narucilac}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class NarucilacResource {

    private final Logger log = LoggerFactory.getLogger(NarucilacResource.class);

    private static final String ENTITY_NAME = "otvoreniNarucilac";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NarucilacRepository narucilacRepository;

    public NarucilacResource(NarucilacRepository narucilacRepository) {
        this.narucilacRepository = narucilacRepository;
    }

    /**
     * {@code POST  /narucilacs} : Create a new narucilac.
     *
     * @param narucilac the narucilac to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new narucilac, or with status {@code 400 (Bad Request)} if the narucilac has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/narucilacs")
    public ResponseEntity<Narucilac> createNarucilac(@RequestBody Narucilac narucilac) throws URISyntaxException {
        log.debug("REST request to save Narucilac : {}", narucilac);
        if (narucilac.getId() != null) {
            throw new BadRequestAlertException("A new narucilac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Narucilac result = narucilacRepository.save(narucilac);
        return ResponseEntity
            .created(new URI("/api/narucilacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /narucilacs/:id} : Updates an existing narucilac.
     *
     * @param id the id of the narucilac to save.
     * @param narucilac the narucilac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated narucilac,
     * or with status {@code 400 (Bad Request)} if the narucilac is not valid,
     * or with status {@code 500 (Internal Server Error)} if the narucilac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/narucilacs/{id}")
    public ResponseEntity<Narucilac> updateNarucilac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Narucilac narucilac
    ) throws URISyntaxException {
        log.debug("REST request to update Narucilac : {}, {}", id, narucilac);
        if (narucilac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, narucilac.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!narucilacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Narucilac result = narucilacRepository.save(narucilac);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, narucilac.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /narucilacs/:id} : Partial updates given fields of an existing narucilac, field will ignore if it is null
     *
     * @param id the id of the narucilac to save.
     * @param narucilac the narucilac to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated narucilac,
     * or with status {@code 400 (Bad Request)} if the narucilac is not valid,
     * or with status {@code 404 (Not Found)} if the narucilac is not found,
     * or with status {@code 500 (Internal Server Error)} if the narucilac couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/narucilacs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Narucilac> partialUpdateNarucilac(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Narucilac narucilac
    ) throws URISyntaxException {
        log.debug("REST request to partial update Narucilac partially : {}, {}", id, narucilac);
        if (narucilac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, narucilac.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!narucilacRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Narucilac> result = narucilacRepository
            .findById(narucilac.getId())
            .map(
                existingNarucilac -> {
                    if (narucilac.getNaziv() != null) {
                        existingNarucilac.setNaziv(narucilac.getNaziv());
                    }
                    if (narucilac.getRacun() != null) {
                        existingNarucilac.setRacun(narucilac.getRacun());
                    }
                    if (narucilac.getTelefon() != null) {
                        existingNarucilac.setTelefon(narucilac.getTelefon());
                    }
                    if (narucilac.getPib() != null) {
                        existingNarucilac.setPib(narucilac.getPib());
                    }
                    if (narucilac.getPdv() != null) {
                        existingNarucilac.setPdv(narucilac.getPdv());
                    }
                    if (narucilac.getOdgovornoLiceNarucioca() != null) {
                        existingNarucilac.setOdgovornoLiceNarucioca(narucilac.getOdgovornoLiceNarucioca());
                    }
                    if (narucilac.getEmail() != null) {
                        existingNarucilac.setEmail(narucilac.getEmail());
                    }
                    if (narucilac.getAdresa() != null) {
                        existingNarucilac.setAdresa(narucilac.getAdresa());
                    }

                    return existingNarucilac;
                }
            )
            .map(narucilacRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, narucilac.getId().toString())
        );
    }

    /**
     * {@code GET  /narucilacs} : get all the narucilacs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of narucilacs in body.
     */
    @GetMapping("/narucilacs")
    public List<Narucilac> getAllNarucilacs() {
        log.debug("REST request to get all Narucilacs");
        return narucilacRepository.findAll();
    }

    /**
     * {@code GET  /narucilacs/:id} : get the "id" narucilac.
     *
     * @param id the id of the narucilac to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the narucilac, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/narucilacs/{id}")
    public ResponseEntity<Narucilac> getNarucilac(@PathVariable Long id) {
        log.debug("REST request to get Narucilac : {}", id);
        Optional<Narucilac> narucilac = narucilacRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(narucilac);
    }

    /**
     * {@code DELETE  /narucilacs/:id} : delete the "id" narucilac.
     *
     * @param id the id of the narucilac to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/narucilacs/{id}")
    public ResponseEntity<Void> deleteNarucilac(@PathVariable Long id) {
        log.debug("REST request to delete Narucilac : {}", id);
        narucilacRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
