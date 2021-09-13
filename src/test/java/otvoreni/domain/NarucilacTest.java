package otvoreni.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import otvoreni.web.rest.TestUtil;

class NarucilacTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Narucilac.class);
        Narucilac narucilac1 = new Narucilac();
        narucilac1.setId(1L);
        Narucilac narucilac2 = new Narucilac();
        narucilac2.setId(narucilac1.getId());
        assertThat(narucilac1).isEqualTo(narucilac2);
        narucilac2.setId(2L);
        assertThat(narucilac1).isNotEqualTo(narucilac2);
        narucilac1.setId(null);
        assertThat(narucilac1).isNotEqualTo(narucilac2);
    }
}
