package otvoreni.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import otvoreni.web.rest.TestUtil;

class PonudjaciPonudeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PonudjaciPonude.class);
        PonudjaciPonude ponudjaciPonude1 = new PonudjaciPonude();
        ponudjaciPonude1.setId(1L);
        PonudjaciPonude ponudjaciPonude2 = new PonudjaciPonude();
        ponudjaciPonude2.setId(ponudjaciPonude1.getId());
        assertThat(ponudjaciPonude1).isEqualTo(ponudjaciPonude2);
        ponudjaciPonude2.setId(2L);
        assertThat(ponudjaciPonude1).isNotEqualTo(ponudjaciPonude2);
        ponudjaciPonude1.setId(null);
        assertThat(ponudjaciPonude1).isNotEqualTo(ponudjaciPonude2);
    }
}
