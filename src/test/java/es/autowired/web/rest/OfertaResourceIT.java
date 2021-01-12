package es.autowired.web.rest;

import es.autowired.AfiliadosApp;
import es.autowired.domain.Oferta;
import es.autowired.repository.OfertaRepository;
import es.autowired.service.OfertaService;
import es.autowired.service.dto.OfertaDTO;
import es.autowired.service.mapper.OfertaMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OfertaResource} REST controller.
 */
@SpringBootTest(classes = AfiliadosApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OfertaResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Double DEFAULT_PRECIO_HABITUAL = 0.01D;
    private static final Double UPDATED_PRECIO_HABITUAL = 1D;

    private static final Double DEFAULT_PRECIO_REBAJADO = 0.1D;
    private static final Double UPDATED_PRECIO_REBAJADO = 1D;

    private static final String DEFAULT_LINK = "Server application generated successfullyK";
    private static final String UPDATED_LINK = "Server application generated successfullyG";

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private OfertaMapper ofertaMapper;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOfertaMockMvc;

    private Oferta oferta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Oferta createEntity(EntityManager em) {
        Oferta oferta = new Oferta()
            .descripcion(DEFAULT_DESCRIPCION)
            .precioHabitual(DEFAULT_PRECIO_HABITUAL)
            .precioRebajado(DEFAULT_PRECIO_REBAJADO)
            .link(DEFAULT_LINK);
        return oferta;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Oferta createUpdatedEntity(EntityManager em) {
        Oferta oferta = new Oferta()
            .descripcion(UPDATED_DESCRIPCION)
            .precioHabitual(UPDATED_PRECIO_HABITUAL)
            .precioRebajado(UPDATED_PRECIO_REBAJADO)
            .link(UPDATED_LINK);
        return oferta;
    }

    @BeforeEach
    public void initTest() {
        oferta = createEntity(em);
    }

    @Test
    @Transactional
    public void createOferta() throws Exception {
        int databaseSizeBeforeCreate = ofertaRepository.findAll().size();
        // Create the Oferta
        OfertaDTO ofertaDTO = ofertaMapper.toDto(oferta);
        restOfertaMockMvc.perform(post("/api/ofertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ofertaDTO)))
            .andExpect(status().isCreated());

        // Validate the Oferta in the database
        List<Oferta> ofertaList = ofertaRepository.findAll();
        assertThat(ofertaList).hasSize(databaseSizeBeforeCreate + 1);
        Oferta testOferta = ofertaList.get(ofertaList.size() - 1);
        assertThat(testOferta.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testOferta.getPrecioHabitual()).isEqualTo(DEFAULT_PRECIO_HABITUAL);
        assertThat(testOferta.getPrecioRebajado()).isEqualTo(DEFAULT_PRECIO_REBAJADO);
        assertThat(testOferta.getLink()).isEqualTo(DEFAULT_LINK);
    }

    @Test
    @Transactional
    public void createOfertaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ofertaRepository.findAll().size();

        // Create the Oferta with an existing ID
        oferta.setId(1L);
        OfertaDTO ofertaDTO = ofertaMapper.toDto(oferta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfertaMockMvc.perform(post("/api/ofertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ofertaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Oferta in the database
        List<Oferta> ofertaList = ofertaRepository.findAll();
        assertThat(ofertaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPrecioHabitualIsRequired() throws Exception {
        int databaseSizeBeforeTest = ofertaRepository.findAll().size();
        // set the field null
        oferta.setPrecioHabitual(null);

        // Create the Oferta, which fails.
        OfertaDTO ofertaDTO = ofertaMapper.toDto(oferta);


        restOfertaMockMvc.perform(post("/api/ofertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ofertaDTO)))
            .andExpect(status().isBadRequest());

        List<Oferta> ofertaList = ofertaRepository.findAll();
        assertThat(ofertaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrecioRebajadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = ofertaRepository.findAll().size();
        // set the field null
        oferta.setPrecioRebajado(null);

        // Create the Oferta, which fails.
        OfertaDTO ofertaDTO = ofertaMapper.toDto(oferta);


        restOfertaMockMvc.perform(post("/api/ofertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ofertaDTO)))
            .andExpect(status().isBadRequest());

        List<Oferta> ofertaList = ofertaRepository.findAll();
        assertThat(ofertaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOfertas() throws Exception {
        // Initialize the database
        ofertaRepository.saveAndFlush(oferta);

        // Get all the ofertaList
        restOfertaMockMvc.perform(get("/api/ofertas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oferta.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].precioHabitual").value(hasItem(DEFAULT_PRECIO_HABITUAL.doubleValue())))
            .andExpect(jsonPath("$.[*].precioRebajado").value(hasItem(DEFAULT_PRECIO_REBAJADO.doubleValue())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)));
    }
    
    @Test
    @Transactional
    public void getOferta() throws Exception {
        // Initialize the database
        ofertaRepository.saveAndFlush(oferta);

        // Get the oferta
        restOfertaMockMvc.perform(get("/api/ofertas/{id}", oferta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(oferta.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.precioHabitual").value(DEFAULT_PRECIO_HABITUAL.doubleValue()))
            .andExpect(jsonPath("$.precioRebajado").value(DEFAULT_PRECIO_REBAJADO.doubleValue()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK));
    }
    @Test
    @Transactional
    public void getNonExistingOferta() throws Exception {
        // Get the oferta
        restOfertaMockMvc.perform(get("/api/ofertas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOferta() throws Exception {
        // Initialize the database
        ofertaRepository.saveAndFlush(oferta);

        int databaseSizeBeforeUpdate = ofertaRepository.findAll().size();

        // Update the oferta
        Oferta updatedOferta = ofertaRepository.findById(oferta.getId()).get();
        // Disconnect from session so that the updates on updatedOferta are not directly saved in db
        em.detach(updatedOferta);
        updatedOferta
            .descripcion(UPDATED_DESCRIPCION)
            .precioHabitual(UPDATED_PRECIO_HABITUAL)
            .precioRebajado(UPDATED_PRECIO_REBAJADO)
            .link(UPDATED_LINK);
        OfertaDTO ofertaDTO = ofertaMapper.toDto(updatedOferta);

        restOfertaMockMvc.perform(put("/api/ofertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ofertaDTO)))
            .andExpect(status().isOk());

        // Validate the Oferta in the database
        List<Oferta> ofertaList = ofertaRepository.findAll();
        assertThat(ofertaList).hasSize(databaseSizeBeforeUpdate);
        Oferta testOferta = ofertaList.get(ofertaList.size() - 1);
        assertThat(testOferta.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testOferta.getPrecioHabitual()).isEqualTo(UPDATED_PRECIO_HABITUAL);
        assertThat(testOferta.getPrecioRebajado()).isEqualTo(UPDATED_PRECIO_REBAJADO);
        assertThat(testOferta.getLink()).isEqualTo(UPDATED_LINK);
    }

    @Test
    @Transactional
    public void updateNonExistingOferta() throws Exception {
        int databaseSizeBeforeUpdate = ofertaRepository.findAll().size();

        // Create the Oferta
        OfertaDTO ofertaDTO = ofertaMapper.toDto(oferta);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfertaMockMvc.perform(put("/api/ofertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ofertaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Oferta in the database
        List<Oferta> ofertaList = ofertaRepository.findAll();
        assertThat(ofertaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOferta() throws Exception {
        // Initialize the database
        ofertaRepository.saveAndFlush(oferta);

        int databaseSizeBeforeDelete = ofertaRepository.findAll().size();

        // Delete the oferta
        restOfertaMockMvc.perform(delete("/api/ofertas/{id}", oferta.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Oferta> ofertaList = ofertaRepository.findAll();
        assertThat(ofertaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
