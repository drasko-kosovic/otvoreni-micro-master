package otvoreni.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import otvoreni.web.rest.TestUtil;

class UgovorPdfTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UgovorPdf.class);
        UgovorPdf ugovorPdf1 = new UgovorPdf();
        ugovorPdf1.setId(1L);
        UgovorPdf ugovorPdf2 = new UgovorPdf();
        ugovorPdf2.setId(ugovorPdf1.getId());
        assertThat(ugovorPdf1).isEqualTo(ugovorPdf2);
        ugovorPdf2.setId(2L);
        assertThat(ugovorPdf1).isNotEqualTo(ugovorPdf2);
        ugovorPdf1.setId(null);
        assertThat(ugovorPdf1).isNotEqualTo(ugovorPdf2);
    }
}
