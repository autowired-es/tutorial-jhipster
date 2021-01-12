package es.autowired.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import es.autowired.web.rest.TestUtil;

public class OfertaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfertaDTO.class);
        OfertaDTO ofertaDTO1 = new OfertaDTO();
        ofertaDTO1.setId(1L);
        OfertaDTO ofertaDTO2 = new OfertaDTO();
        assertThat(ofertaDTO1).isNotEqualTo(ofertaDTO2);
        ofertaDTO2.setId(ofertaDTO1.getId());
        assertThat(ofertaDTO1).isEqualTo(ofertaDTO2);
        ofertaDTO2.setId(2L);
        assertThat(ofertaDTO1).isNotEqualTo(ofertaDTO2);
        ofertaDTO1.setId(null);
        assertThat(ofertaDTO1).isNotEqualTo(ofertaDTO2);
    }
}
