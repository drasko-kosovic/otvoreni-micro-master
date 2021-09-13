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
import otvoreni.domain.UgovorPdf;
import otvoreni.repository.UgovorPdfRepository;
import otvoreni.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link otvoreni.domain.UgovorPdf}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UgovorPdfResource {

    private final Logger log = LoggerFactory.getLogger(UgovorPdfResource.class);

    private final UgovorPdfRepository ugovorPdfRepository;

    public UgovorPdfResource(UgovorPdfRepository ugovorPdfRepository) {
        this.ugovorPdfRepository = ugovorPdfRepository;
    }

    /**
     * {@code GET  /ugovor-pdfs} : get all the ugovorPdfs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ugovorPdfs in body.
     */
    @GetMapping("/ugovor-pdfs")
    public List<UgovorPdf> getAllUgovorPdfs() {
        log.debug("REST request to get all UgovorPdfs");
        return ugovorPdfRepository.findAll();
    }

    /**
     * {@code GET  /ugovor-pdfs/:id} : get the "id" ugovorPdf.
     *
     * @param id the id of the ugovorPdf to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ugovorPdf, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ugovor-pdfs/{id}")
    public ResponseEntity<UgovorPdf> getUgovorPdf(@PathVariable Long id) {
        log.debug("REST request to get UgovorPdf : {}", id);
        Optional<UgovorPdf> ugovorPdf = ugovorPdfRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ugovorPdf);
    }
}
