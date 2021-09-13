package otvoreni.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import otvoreni.web.rest.TestUtil;

class AnexTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Anex.class);
        Anex anex1 = new Anex();
        anex1.setId(1L);
        Anex anex2 = new Anex();
        anex2.setId(anex1.getId());
        assertThat(anex1).isEqualTo(anex2);
        anex2.setId(2L);
        assertThat(anex1).isNotEqualTo(anex2);
        anex1.setId(null);
        assertThat(anex1).isNotEqualTo(anex2);
    }
}
