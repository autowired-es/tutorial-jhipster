package es.autowired.service.mapper;


import es.autowired.domain.*;
import es.autowired.service.dto.OfertaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Oferta} and its DTO {@link OfertaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OfertaMapper extends EntityMapper<OfertaDTO, Oferta> {



    default Oferta fromId(Long id) {
        if (id == null) {
            return null;
        }
        Oferta oferta = new Oferta();
        oferta.setId(id);
        return oferta;
    }
}
