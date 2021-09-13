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
import otvoreni.domain.Anex;
import otvoreni.repository.AnexRepository;
import otvoreni.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link otvoreni.domain.Anex}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AnexResource {

    private final Logger log = LoggerFactory.getLogger(AnexResource.class);

    private static final String ENTITY_NAME = "otvoreniAnex";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnexRepository anexRepository;

    public AnexResource(AnexRepository anexRepository) {
        this.anexRepository = anexRepository;
    }

    /**
     * {@code POST  /anexes} : Create a new anex.
     *
     * @param anex the anex to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new anex, or with status {@code 400 (Bad Request)} if the anex has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/anexes")
    public ResponseEntity<Anex> createAnex(@RequestBody Anex anex) throws URISyntaxException {
        log.debug("REST request to save Anex : {}", anex);
        if (anex.getId() != null) {
            throw new BadRequestAlertException("A new anex cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Anex result = anexRepository.save(anex);
        return ResponseEntity
            .created(new URI("/api/anexes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /anexes/:id} : Updates an existing anex.
     *
     * @param id the id of the anex to save.
     * @param anex the anex to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated anex,
     * or with status {@code 400 (Bad Request)} if the anex is not valid,
     * or with status {@code 500 (Internal Server Error)} if the anex couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/anexes/{id}")
    public ResponseEntity<Anex> updateAnex(@PathVariable(value = "id", required = false) final Long id, @RequestBody Anex anex)
        throws URISyntaxException {
        log.debug("REST request to update Anex : {}, {}", id, anex);
        if (anex.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, anex.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!anexRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Anex result = anexRepository.save(anex);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, anex.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /anexes/:id} : Partial updates given fields of an existing anex, field will ignore if it is null
     *
     * @param id the id of the anex to save.
     * @param anex the anex to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated anex,
     * or with status {@code 400 (Bad Request)} if the anex is not valid,
     * or with status {@code 404 (Not Found)} if the anex is not found,
     * or with status {@code 500 (Internal Server Error)} if the anex couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/anexes/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Anex> partialUpdateAnex(@PathVariable(value = "id", required = false) final Long id, @RequestBody Anex anex)
        throws URISyntaxException {
        log.debug("REST request to partial update Anex partially : {}, {}", id, anex);
        if (anex.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, anex.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!anexRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Anex> result = anexRepository
            .findById(anex.getId())
            .map(
                existingAnex -> {
                    if (anex.getSifra_postupka() != null) {
                        existingAnex.setSifra_postupka(anex.getSifra_postupka());
                    }
                    if (anex.getSifra_ponude() != null) {
                        existingAnex.setSifra_ponude(anex.getSifra_ponude());
                    }
                    if (anex.getAtc() != null) {
                        existingAnex.setAtc(anex.getAtc());
                    }
                    if (anex.getInn() != null) {
                        existingAnex.setInn(anex.getInn());
                    }
                    if (anex.getZasticeni_naziv() != null) {
                        existingAnex.setZasticeni_naziv(anex.getZasticeni_naziv());
                    }
                    if (anex.getFarmaceutski_oblik_lijeka() != null) {
                        existingAnex.setFarmaceutski_oblik_lijeka(anex.getFarmaceutski_oblik_lijeka());
                    }
                    if (anex.getJacina_lijeka() != null) {
                        existingAnex.setJacina_lijeka(anex.getJacina_lijeka());
                    }
                    if (anex.getPakovanje() != null) {
                        existingAnex.setPakovanje(anex.getPakovanje());
                    }
                    if (anex.getTrazena_kolicina() != null) {
                        existingAnex.setTrazena_kolicina(anex.getTrazena_kolicina());
                    }
                    if (anex.getProcijenjena_vrijednost() != null) {
                        existingAnex.setProcijenjena_vrijednost(anex.getProcijenjena_vrijednost());
                    }
                    if (anex.getRok_isporuke() != null) {
                        existingAnex.setRok_isporuke(anex.getRok_isporuke());
                    }
                    if (anex.getNaziv_ponudjaca() != null) {
                        existingAnex.setNaziv_ponudjaca(anex.getNaziv_ponudjaca());
                    }
                    if (anex.getNaziv_proizvodjaca() != null) {
                        existingAnex.setNaziv_proizvodjaca(anex.getNaziv_proizvodjaca());
                    }

                    return existingAnex;
                }
            )
            .map(anexRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, anex.getId().toString())
        );
    }

    /**
     * {@code GET  /anexes} : get all the anexes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of anexes in body.
     */
    @GetMapping("/anexes")
    public List<Anex> getAllAnexes() {
        log.debug("REST request to get all Anexes");
        return anexRepository.findAll();
    }

    /**
     * {@code GET  /anexes/:id} : get the "id" anex.
     *
     * @param id the id of the anex to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the anex, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/anexes/{id}")
    public ResponseEntity<Anex> getAnex(@PathVariable Long id) {
        log.debug("REST request to get Anex : {}", id);
        Optional<Anex> anex = anexRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(anex);
    }

    /**
     * {@code DELETE  /anexes/:id} : delete the "id" anex.
     *
     * @param id the id of the anex to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/anexes/{id}")
    public ResponseEntity<Void> deleteAnex(@PathVariable Long id) {
        log.debug("REST request to delete Anex : {}", id);
        anexRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
