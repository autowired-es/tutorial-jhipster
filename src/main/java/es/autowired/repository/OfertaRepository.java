package es.autowired.repository;

import es.autowired.domain.Oferta;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Oferta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {
}
