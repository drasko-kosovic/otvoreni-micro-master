package otvoreni.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import otvoreni.IntegrationTest;
import otvoreni.domain.UgovorPdf;
import otvoreni.repository.UgovorPdfRepository;

/**
 * Integration tests for the {@link UgovorPdfResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UgovorPdfResourceIT {

    private static final String DEFAULT_BROJ_UGOVORA = "AAAAAAAAAA";
    private static final String UPDATED_BROJ_UGOVORA = "BBBBBBBBBB";

    private static final String DEFAULT_BROJ_TENDERA = "AAAAAAAAAA";
    private static final String UPDATED_BROJ_TENDERA = "BBBBBBBBBB";

    private static final String DEFAULT_BROJ_ODLUKE = "AAAAAAAAAA";
    private static final String UPDATED_BROJ_ODLUKE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATUM_UGOVORA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_UGOVORA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATUM_PONUDE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_PONUDE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATUM_ODLUKE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_ODLUKE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PREDMET_UGOVORA = "AAAAAAAAAA";
    private static final String UPDATED_PREDMET_UGOVORA = "BBBBBBBBBB";

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFON = "AAAAAAAAAA";
    private static final String UPDATED_TELEFON = "BBBBBBBBBB";

    private static final String DEFAULT_NAZIV_PONUDJACA = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_PONUDJACA = "BBBBBBBBBB";

    private static final String DEFAULT_BROJ_DATUM_TENDERSKE_DOKUMNTACIJE = "AAAAAAAAAA";
    private static final String UPDATED_BROJ_DATUM_TENDERSKE_DOKUMNTACIJE = "BBBBBBBBBB";

    private static final String DEFAULT_BROJ_DATUM_ODLUKE = "AAAAAAAAAA";
    private static final String UPDATED_BROJ_DATUM_ODLUKE = "BBBBBBBBBB";

    private static final String DEFAULT_BROJ_DATUM_PONUDE = "AAAAAAAAAA";
    private static final String UPDATED_BROJ_DATUM_PONUDE = "BBBBBBBBBB";

    private static final Double DEFAULT_IZNOS_UGOVORA_BEZ_PDF = 1D;
    private static final Double UPDATED_IZNOS_UGOVORA_BEZ_PDF = 2D;

    private static final Integer DEFAULT_SIFRA_POSTUPKA = 1;
    private static final Integer UPDATED_SIFRA_POSTUPKA = 2;

    private static final Integer DEFAULT_SIFRA_PONUDE = 1;
    private static final Integer UPDATED_SIFRA_PONUDE = 2;

    private static final String DEFAULT_ODGOVORNO_LICE = "AAAAAAAAAA";
    private static final String UPDATED_ODGOVORNO_LICE = "BBBBBBBBBB";

    private static final String DEFAULT_ODGOVORNO_LICE_NARUCIOCA = "AAAAAAAAAA";
    private static final String UPDATED_ODGOVORNO_LICE_NARUCIOCA = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESA = "AAAAAAAAAA";
    private static final String UPDATED_ADRESA = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESA_PONUDJACA = "AAAAAAAAAA";
    private static final String UPDATED_ADRESA_PONUDJACA = "BBBBBBBBBB";

    private static final String DEFAULT_BANKA_RACUN = "AAAAAAAAAA";
    private static final String UPDATED_BANKA_RACUN = "BBBBBBBBBB";

    private static final String DEFAULT_RACUN = "AAAAAAAAAA";
    private static final String UPDATED_RACUN = "BBBBBBBBBB";

    private static final String DEFAULT_PIB = "AAAAAAAAAA";
    private static final String UPDATED_PIB = "BBBBBBBBBB";

    private static final String DEFAULT_PDV = "AAAAAAAAAA";
    private static final String UPDATED_PDV = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATUM_OBJAVE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_OBJAVE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_VRSTA_POSTUPKA = "AAAAAAAAAA";
    private static final String UPDATED_VRSTA_POSTUPKA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ugovor-pdfs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UgovorPdfRepository ugovorPdfRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUgovorPdfMockMvc;

    private UgovorPdf ugovorPdf;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UgovorPdf createEntity(EntityManager em) {
        UgovorPdf ugovorPdf = new UgovorPdf()
            .broj_ugovora(DEFAULT_BROJ_UGOVORA)
            .broj_tendera(DEFAULT_BROJ_TENDERA)
            .broj_odluke(DEFAULT_BROJ_ODLUKE)
            .datum_ugovora(DEFAULT_DATUM_UGOVORA)
            .datum_ponude(DEFAULT_DATUM_PONUDE)
            .datum_odluke(DEFAULT_DATUM_ODLUKE)
            .predmet_ugovora(DEFAULT_PREDMET_UGOVORA)
            .naziv(DEFAULT_NAZIV)
            .telefon(DEFAULT_TELEFON)
            .naziv_ponudjaca(DEFAULT_NAZIV_PONUDJACA)
            .broj_datum_tenderske_dokumntacije(DEFAULT_BROJ_DATUM_TENDERSKE_DOKUMNTACIJE)
            .broj_datum_odluke(DEFAULT_BROJ_DATUM_ODLUKE)
            .broj_datum_ponude(DEFAULT_BROJ_DATUM_PONUDE)
            .iznos_ugovora_bez_pdf(DEFAULT_IZNOS_UGOVORA_BEZ_PDF)
            .sifra_postupka(DEFAULT_SIFRA_POSTUPKA)
            .sifra_ponude(DEFAULT_SIFRA_PONUDE)
            .odgovorno_lice(DEFAULT_ODGOVORNO_LICE)
            .odgovorno_lice_narucioca(DEFAULT_ODGOVORNO_LICE_NARUCIOCA)
            .adresa(DEFAULT_ADRESA)
            .adresa_ponudjaca(DEFAULT_ADRESA_PONUDJACA)
            .banka_racun(DEFAULT_BANKA_RACUN)
            .racun(DEFAULT_RACUN)
            .pib(DEFAULT_PIB)
            .pdv(DEFAULT_PDV)
            .datum_objave(DEFAULT_DATUM_OBJAVE)
            .vrsta_postupka(DEFAULT_VRSTA_POSTUPKA);
        return ugovorPdf;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UgovorPdf createUpdatedEntity(EntityManager em) {
        UgovorPdf ugovorPdf = new UgovorPdf()
            .broj_ugovora(UPDATED_BROJ_UGOVORA)
            .broj_tendera(UPDATED_BROJ_TENDERA)
            .broj_odluke(UPDATED_BROJ_ODLUKE)
            .datum_ugovora(UPDATED_DATUM_UGOVORA)
            .datum_ponude(UPDATED_DATUM_PONUDE)
            .datum_odluke(UPDATED_DATUM_ODLUKE)
            .predmet_ugovora(UPDATED_PREDMET_UGOVORA)
            .naziv(UPDATED_NAZIV)
            .telefon(UPDATED_TELEFON)
            .naziv_ponudjaca(UPDATED_NAZIV_PONUDJACA)
            .broj_datum_tenderske_dokumntacije(UPDATED_BROJ_DATUM_TENDERSKE_DOKUMNTACIJE)
            .broj_datum_odluke(UPDATED_BROJ_DATUM_ODLUKE)
            .broj_datum_ponude(UPDATED_BROJ_DATUM_PONUDE)
            .iznos_ugovora_bez_pdf(UPDATED_IZNOS_UGOVORA_BEZ_PDF)
            .sifra_postupka(UPDATED_SIFRA_POSTUPKA)
            .sifra_ponude(UPDATED_SIFRA_PONUDE)
            .odgovorno_lice(UPDATED_ODGOVORNO_LICE)
            .odgovorno_lice_narucioca(UPDATED_ODGOVORNO_LICE_NARUCIOCA)
            .adresa(UPDATED_ADRESA)
            .adresa_ponudjaca(UPDATED_ADRESA_PONUDJACA)
            .banka_racun(UPDATED_BANKA_RACUN)
            .racun(UPDATED_RACUN)
            .pib(UPDATED_PIB)
            .pdv(UPDATED_PDV)
            .datum_objave(UPDATED_DATUM_OBJAVE)
            .vrsta_postupka(UPDATED_VRSTA_POSTUPKA);
        return ugovorPdf;
    }

    @BeforeEach
    public void initTest() {
        ugovorPdf = createEntity(em);
    }

    @Test
    @Transactional
    void getAllUgovorPdfs() throws Exception {
        // Initialize the database
        ugovorPdfRepository.saveAndFlush(ugovorPdf);

        // Get all the ugovorPdfList
        restUgovorPdfMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ugovorPdf.getId().intValue())))
            .andExpect(jsonPath("$.[*].broj_ugovora").value(hasItem(DEFAULT_BROJ_UGOVORA)))
            .andExpect(jsonPath("$.[*].broj_tendera").value(hasItem(DEFAULT_BROJ_TENDERA)))
            .andExpect(jsonPath("$.[*].broj_odluke").value(hasItem(DEFAULT_BROJ_ODLUKE)))
            .andExpect(jsonPath("$.[*].datum_ugovora").value(hasItem(DEFAULT_DATUM_UGOVORA.toString())))
            .andExpect(jsonPath("$.[*].datum_ponude").value(hasItem(DEFAULT_DATUM_PONUDE.toString())))
            .andExpect(jsonPath("$.[*].datum_odluke").value(hasItem(DEFAULT_DATUM_ODLUKE.toString())))
            .andExpect(jsonPath("$.[*].predmet_ugovora").value(hasItem(DEFAULT_PREDMET_UGOVORA)))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV)))
            .andExpect(jsonPath("$.[*].telefon").value(hasItem(DEFAULT_TELEFON)))
            .andExpect(jsonPath("$.[*].naziv_ponudjaca").value(hasItem(DEFAULT_NAZIV_PONUDJACA)))
            .andExpect(jsonPath("$.[*].broj_datum_tenderske_dokumntacije").value(hasItem(DEFAULT_BROJ_DATUM_TENDERSKE_DOKUMNTACIJE)))
            .andExpect(jsonPath("$.[*].broj_datum_odluke").value(hasItem(DEFAULT_BROJ_DATUM_ODLUKE)))
            .andExpect(jsonPath("$.[*].broj_datum_ponude").value(hasItem(DEFAULT_BROJ_DATUM_PONUDE)))
            .andExpect(jsonPath("$.[*].iznos_ugovora_bez_pdf").value(hasItem(DEFAULT_IZNOS_UGOVORA_BEZ_PDF.doubleValue())))
            .andExpect(jsonPath("$.[*].sifra_postupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].sifra_ponude").value(hasItem(DEFAULT_SIFRA_PONUDE)))
            .andExpect(jsonPath("$.[*].odgovorno_lice").value(hasItem(DEFAULT_ODGOVORNO_LICE)))
            .andExpect(jsonPath("$.[*].odgovorno_lice_narucioca").value(hasItem(DEFAULT_ODGOVORNO_LICE_NARUCIOCA)))
            .andExpect(jsonPath("$.[*].adresa").value(hasItem(DEFAULT_ADRESA)))
            .andExpect(jsonPath("$.[*].adresa_ponudjaca").value(hasItem(DEFAULT_ADRESA_PONUDJACA)))
            .andExpect(jsonPath("$.[*].banka_racun").value(hasItem(DEFAULT_BANKA_RACUN)))
            .andExpect(jsonPath("$.[*].racun").value(hasItem(DEFAULT_RACUN)))
            .andExpect(jsonPath("$.[*].pib").value(hasItem(DEFAULT_PIB)))
            .andExpect(jsonPath("$.[*].pdv").value(hasItem(DEFAULT_PDV)))
            .andExpect(jsonPath("$.[*].datum_objave").value(hasItem(DEFAULT_DATUM_OBJAVE.toString())))
            .andExpect(jsonPath("$.[*].vrsta_postupka").value(hasItem(DEFAULT_VRSTA_POSTUPKA)));
    }

    @Test
    @Transactional
    void getUgovorPdf() throws Exception {
        // Initialize the database
        ugovorPdfRepository.saveAndFlush(ugovorPdf);

        // Get the ugovorPdf
        restUgovorPdfMockMvc
            .perform(get(ENTITY_API_URL_ID, ugovorPdf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ugovorPdf.getId().intValue()))
            .andExpect(jsonPath("$.broj_ugovora").value(DEFAULT_BROJ_UGOVORA))
            .andExpect(jsonPath("$.broj_tendera").value(DEFAULT_BROJ_TENDERA))
            .andExpect(jsonPath("$.broj_odluke").value(DEFAULT_BROJ_ODLUKE))
            .andExpect(jsonPath("$.datum_ugovora").value(DEFAULT_DATUM_UGOVORA.toString()))
            .andExpect(jsonPath("$.datum_ponude").value(DEFAULT_DATUM_PONUDE.toString()))
            .andExpect(jsonPath("$.datum_odluke").value(DEFAULT_DATUM_ODLUKE.toString()))
            .andExpect(jsonPath("$.predmet_ugovora").value(DEFAULT_PREDMET_UGOVORA))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV))
            .andExpect(jsonPath("$.telefon").value(DEFAULT_TELEFON))
            .andExpect(jsonPath("$.naziv_ponudjaca").value(DEFAULT_NAZIV_PONUDJACA))
            .andExpect(jsonPath("$.broj_datum_tenderske_dokumntacije").value(DEFAULT_BROJ_DATUM_TENDERSKE_DOKUMNTACIJE))
            .andExpect(jsonPath("$.broj_datum_odluke").value(DEFAULT_BROJ_DATUM_ODLUKE))
            .andExpect(jsonPath("$.broj_datum_ponude").value(DEFAULT_BROJ_DATUM_PONUDE))
            .andExpect(jsonPath("$.iznos_ugovora_bez_pdf").value(DEFAULT_IZNOS_UGOVORA_BEZ_PDF.doubleValue()))
            .andExpect(jsonPath("$.sifra_postupka").value(DEFAULT_SIFRA_POSTUPKA))
            .andExpect(jsonPath("$.sifra_ponude").value(DEFAULT_SIFRA_PONUDE))
            .andExpect(jsonPath("$.odgovorno_lice").value(DEFAULT_ODGOVORNO_LICE))
            .andExpect(jsonPath("$.odgovorno_lice_narucioca").value(DEFAULT_ODGOVORNO_LICE_NARUCIOCA))
            .andExpect(jsonPath("$.adresa").value(DEFAULT_ADRESA))
            .andExpect(jsonPath("$.adresa_ponudjaca").value(DEFAULT_ADRESA_PONUDJACA))
            .andExpect(jsonPath("$.banka_racun").value(DEFAULT_BANKA_RACUN))
            .andExpect(jsonPath("$.racun").value(DEFAULT_RACUN))
            .andExpect(jsonPath("$.pib").value(DEFAULT_PIB))
            .andExpect(jsonPath("$.pdv").value(DEFAULT_PDV))
            .andExpect(jsonPath("$.datum_objave").value(DEFAULT_DATUM_OBJAVE.toString()))
            .andExpect(jsonPath("$.vrsta_postupka").value(DEFAULT_VRSTA_POSTUPKA));
    }

    @Test
    @Transactional
    void getNonExistingUgovorPdf() throws Exception {
        // Get the ugovorPdf
        restUgovorPdfMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
