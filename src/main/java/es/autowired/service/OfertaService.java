package es.autowired.service;

import es.autowired.service.dto.OfertaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link es.autowired.domain.Oferta}.
 */
public interface OfertaService {

    /**
     * Save a oferta.
     *
     * @param ofertaDTO the entity to save.
     * @return the persisted entity.
     */
    OfertaDTO save(OfertaDTO ofertaDTO);

    /**
     * Get all the ofertas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OfertaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" oferta.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OfertaDTO> findOne(Long id);

    /**
     * Delete the "id" oferta.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
