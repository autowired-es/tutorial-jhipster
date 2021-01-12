package es.autowired.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OfertaMapperTest {

    private OfertaMapper ofertaMapper;

    @BeforeEach
    public void setUp() {
        ofertaMapper = new OfertaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ofertaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ofertaMapper.fromId(null)).isNull();
    }
}
