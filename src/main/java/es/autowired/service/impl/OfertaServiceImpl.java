package es.autowired.service.impl;

import es.autowired.service.OfertaService;
import es.autowired.domain.Oferta;
import es.autowired.repository.OfertaRepository;
import es.autowired.service.dto.OfertaDTO;
import es.autowired.service.mapper.OfertaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Oferta}.
 */
@Service
@Transactional
public class OfertaServiceImpl implements OfertaService {

    private final Logger log = LoggerFactory.getLogger(OfertaServiceImpl.class);

    private final OfertaRepository ofertaRepository;

    private final OfertaMapper ofertaMapper;

    public OfertaServiceImpl(OfertaRepository ofertaRepository, OfertaMapper ofertaMapper) {
        this.ofertaRepository = ofertaRepository;
        this.ofertaMapper = ofertaMapper;
    }

    @Override
    public OfertaDTO save(OfertaDTO ofertaDTO) {
        log.debug("Request to save Oferta : {}", ofertaDTO);
        Oferta oferta = ofertaMapper.toEntity(ofertaDTO);
        oferta = ofertaRepository.save(oferta);
        return ofertaMapper.toDto(oferta);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OfertaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ofertas");
        return ofertaRepository.findAll(pageable)
            .map(ofertaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OfertaDTO> findOne(Long id) {
        log.debug("Request to get Oferta : {}", id);
        return ofertaRepository.findById(id)
            .map(ofertaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Oferta : {}", id);
        ofertaRepository.deleteById(id);
    }
}
