package es.autowired.web.rest;

import es.autowired.service.OfertaService;
import es.autowired.web.rest.errors.BadRequestAlertException;
import es.autowired.service.dto.OfertaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link es.autowired.domain.Oferta}.
 */
@RestController
@RequestMapping("/api")
public class OfertaResource {

    private final Logger log = LoggerFactory.getLogger(OfertaResource.class);

    private static final String ENTITY_NAME = "oferta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfertaService ofertaService;

    public OfertaResource(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    /**
     * {@code POST  /ofertas} : Create a new oferta.
     *
     * @param ofertaDTO the ofertaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ofertaDTO, or with status {@code 400 (Bad Request)} if the oferta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ofertas")
    public ResponseEntity<OfertaDTO> createOferta(@Valid @RequestBody OfertaDTO ofertaDTO) throws URISyntaxException {
        log.debug("REST request to save Oferta : {}", ofertaDTO);
        if (ofertaDTO.getId() != null) {
            throw new BadRequestAlertException("A new oferta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfertaDTO result = ofertaService.save(ofertaDTO);
        return ResponseEntity.created(new URI("/api/ofertas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ofertas} : Updates an existing oferta.
     *
     * @param ofertaDTO the ofertaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ofertaDTO,
     * or with status {@code 400 (Bad Request)} if the ofertaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ofertaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ofertas")
    public ResponseEntity<OfertaDTO> updateOferta(@Valid @RequestBody OfertaDTO ofertaDTO) throws URISyntaxException {
        log.debug("REST request to update Oferta : {}", ofertaDTO);
        if (ofertaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfertaDTO result = ofertaService.save(ofertaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ofertaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ofertas} : get all the ofertas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ofertas in body.
     */
    @GetMapping("/ofertas")
    public ResponseEntity<List<OfertaDTO>> getAllOfertas(Pageable pageable) {
        log.debug("REST request to get a page of Ofertas");
        Page<OfertaDTO> page = ofertaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ofertas/:id} : get the "id" oferta.
     *
     * @param id the id of the ofertaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ofertaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ofertas/{id}")
    public ResponseEntity<OfertaDTO> getOferta(@PathVariable Long id) {
        log.debug("REST request to get Oferta : {}", id);
        Optional<OfertaDTO> ofertaDTO = ofertaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ofertaDTO);
    }

    /**
     * {@code DELETE  /ofertas/:id} : delete the "id" oferta.
     *
     * @param id the id of the ofertaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ofertas/{id}")
    public ResponseEntity<Void> deleteOferta(@PathVariable Long id) {
        log.debug("REST request to delete Oferta : {}", id);
        ofertaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
