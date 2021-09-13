package otvoreni.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import otvoreni.domain.Prvorangirani;
import otvoreni.repository.PrvorangiraniRepository;

/**
 * Integration tests for the {@link PrvorangiraniResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PrvorangiraniResourceIT {

    private static final Integer DEFAULT_SIFRA_POSTUPKA = 1;
    private static final Integer UPDATED_SIFRA_POSTUPKA = 2;

    private static final Integer DEFAULT_SIFRA_PONUDE = 1;
    private static final Integer UPDATED_SIFRA_PONUDE = 2;

    private static final String DEFAULT_ATC = "AAAAAAAAAA";
    private static final String UPDATED_ATC = "BBBBBBBBBB";

    private static final String DEFAULT_INN = "AAAAAAAAAA";
    private static final String UPDATED_INN = "BBBBBBBBBB";

    private static final String DEFAULT_ZASTICENI_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_ZASTICENI_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA = "AAAAAAAAAA";
    private static final String UPDATED_FARMACEUTSKI_OBLIK_LIJEKA = "BBBBBBBBBB";

    private static final String DEFAULT_JACINA_LIJEKA = "AAAAAAAAAA";
    private static final String UPDATED_JACINA_LIJEKA = "BBBBBBBBBB";

    private static final String DEFAULT_PAKOVANJE = "AAAAAAAAAA";
    private static final String UPDATED_PAKOVANJE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRAZENA_KOLICINA = 1;
    private static final Integer UPDATED_TRAZENA_KOLICINA = 2;

    private static final Double DEFAULT_PROCIJENJENA_VRIJEDNOST = 1D;
    private static final Double UPDATED_PROCIJENJENA_VRIJEDNOST = 2D;

    private static final Integer DEFAULT_ROK_ISPORUKE = 1;
    private static final Integer UPDATED_ROK_ISPORUKE = 2;

    private static final String DEFAULT_NAZIV_PROIZVODJACA = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_PROIZVODJACA = "BBBBBBBBBB";

    private static final String DEFAULT_NAZIV_PONUDJACA = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_PONUDJACA = "BBBBBBBBBB";

    private static final Double DEFAULT_BOD_CIJENA = 1D;
    private static final Double UPDATED_BOD_CIJENA = 2D;

    private static final Double DEFAULT_BOD_ROK = 1D;
    private static final Double UPDATED_BOD_ROK = 2D;

    private static final Double DEFAULT_BOD_UKUPNO = 1D;
    private static final Double UPDATED_BOD_UKUPNO = 2D;

    private static final String ENTITY_API_URL = "/api/prvorangiranis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PrvorangiraniRepository prvorangiraniRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrvorangiraniMockMvc;

    private Prvorangirani prvorangirani;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prvorangirani createEntity(EntityManager em) {
        Prvorangirani prvorangirani = new Prvorangirani()
            .sifraPostupka(DEFAULT_SIFRA_POSTUPKA)
            .sifraPonude(DEFAULT_SIFRA_PONUDE)
            .atc(DEFAULT_ATC)
            .inn(DEFAULT_INN)
            .zasticeniNaziv(DEFAULT_ZASTICENI_NAZIV)
            .farmaceutskiOblikLijeka(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacinaLijeka(DEFAULT_JACINA_LIJEKA)
            .pakovanje(DEFAULT_PAKOVANJE)
            .trazenaKolicina(DEFAULT_TRAZENA_KOLICINA)
            .procijenjenaVrijednost(DEFAULT_PROCIJENJENA_VRIJEDNOST)
            .rokIsporuke(DEFAULT_ROK_ISPORUKE)
            .nazivProizvodjaca(DEFAULT_NAZIV_PROIZVODJACA)
            .nazivPonudjaca(DEFAULT_NAZIV_PONUDJACA)
            .bodCijena(DEFAULT_BOD_CIJENA)
            .bodRok(DEFAULT_BOD_ROK)
            .bodUkupno(DEFAULT_BOD_UKUPNO);
        return prvorangirani;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prvorangirani createUpdatedEntity(EntityManager em) {
        Prvorangirani prvorangirani = new Prvorangirani()
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .sifraPonude(UPDATED_SIFRA_PONUDE)
            .atc(UPDATED_ATC)
            .inn(UPDATED_INN)
            .zasticeniNaziv(UPDATED_ZASTICENI_NAZIV)
            .farmaceutskiOblikLijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacinaLijeka(UPDATED_JACINA_LIJEKA)
            .pakovanje(UPDATED_PAKOVANJE)
            .trazenaKolicina(UPDATED_TRAZENA_KOLICINA)
            .procijenjenaVrijednost(UPDATED_PROCIJENJENA_VRIJEDNOST)
            .rokIsporuke(UPDATED_ROK_ISPORUKE)
            .nazivProizvodjaca(UPDATED_NAZIV_PROIZVODJACA)
            .nazivPonudjaca(UPDATED_NAZIV_PONUDJACA)
            .bodCijena(UPDATED_BOD_CIJENA)
            .bodRok(UPDATED_BOD_ROK)
            .bodUkupno(UPDATED_BOD_UKUPNO);
        return prvorangirani;
    }

    @BeforeEach
    public void initTest() {
        prvorangirani = createEntity(em);
    }

    @Test
    @Transactional
    void getAllPrvorangiranis() throws Exception {
        // Initialize the database
        prvorangiraniRepository.saveAndFlush(prvorangirani);

        // Get all the prvorangiraniList
        restPrvorangiraniMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prvorangirani.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraPostupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].sifraPonude").value(hasItem(DEFAULT_SIFRA_PONUDE)))
            .andExpect(jsonPath("$.[*].atc").value(hasItem(DEFAULT_ATC)))
            .andExpect(jsonPath("$.[*].inn").value(hasItem(DEFAULT_INN)))
            .andExpect(jsonPath("$.[*].zasticeniNaziv").value(hasItem(DEFAULT_ZASTICENI_NAZIV)))
            .andExpect(jsonPath("$.[*].farmaceutskiOblikLijeka").value(hasItem(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)))
            .andExpect(jsonPath("$.[*].jacinaLijeka").value(hasItem(DEFAULT_JACINA_LIJEKA)))
            .andExpect(jsonPath("$.[*].pakovanje").value(hasItem(DEFAULT_PAKOVANJE)))
            .andExpect(jsonPath("$.[*].trazenaKolicina").value(hasItem(DEFAULT_TRAZENA_KOLICINA)))
            .andExpect(jsonPath("$.[*].procijenjenaVrijednost").value(hasItem(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].rokIsporuke").value(hasItem(DEFAULT_ROK_ISPORUKE)))
            .andExpect(jsonPath("$.[*].nazivProizvodjaca").value(hasItem(DEFAULT_NAZIV_PROIZVODJACA)))
            .andExpect(jsonPath("$.[*].nazivPonudjaca").value(hasItem(DEFAULT_NAZIV_PONUDJACA)))
            .andExpect(jsonPath("$.[*].bodCijena").value(hasItem(DEFAULT_BOD_CIJENA.doubleValue())))
            .andExpect(jsonPath("$.[*].bodRok").value(hasItem(DEFAULT_BOD_ROK.doubleValue())))
            .andExpect(jsonPath("$.[*].bodUkupno").value(hasItem(DEFAULT_BOD_UKUPNO.doubleValue())));
    }

    @Test
    @Transactional
    void getPrvorangirani() throws Exception {
        // Initialize the database
        prvorangiraniRepository.saveAndFlush(prvorangirani);

        // Get the prvorangirani
        restPrvorangiraniMockMvc
            .perform(get(ENTITY_API_URL_ID, prvorangirani.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prvorangirani.getId().intValue()))
            .andExpect(jsonPath("$.sifraPostupka").value(DEFAULT_SIFRA_POSTUPKA))
            .andExpect(jsonPath("$.sifraPonude").value(DEFAULT_SIFRA_PONUDE))
            .andExpect(jsonPath("$.atc").value(DEFAULT_ATC))
            .andExpect(jsonPath("$.inn").value(DEFAULT_INN))
            .andExpect(jsonPath("$.zasticeniNaziv").value(DEFAULT_ZASTICENI_NAZIV))
            .andExpect(jsonPath("$.farmaceutskiOblikLijeka").value(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA))
            .andExpect(jsonPath("$.jacinaLijeka").value(DEFAULT_JACINA_LIJEKA))
            .andExpect(jsonPath("$.pakovanje").value(DEFAULT_PAKOVANJE))
            .andExpect(jsonPath("$.trazenaKolicina").value(DEFAULT_TRAZENA_KOLICINA))
            .andExpect(jsonPath("$.procijenjenaVrijednost").value(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue()))
            .andExpect(jsonPath("$.rokIsporuke").value(DEFAULT_ROK_ISPORUKE))
            .andExpect(jsonPath("$.nazivProizvodjaca").value(DEFAULT_NAZIV_PROIZVODJACA))
            .andExpect(jsonPath("$.nazivPonudjaca").value(DEFAULT_NAZIV_PONUDJACA))
            .andExpect(jsonPath("$.bodCijena").value(DEFAULT_BOD_CIJENA.doubleValue()))
            .andExpect(jsonPath("$.bodRok").value(DEFAULT_BOD_ROK.doubleValue()))
            .andExpect(jsonPath("$.bodUkupno").value(DEFAULT_BOD_UKUPNO.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingPrvorangirani() throws Exception {
        // Get the prvorangirani
        restPrvorangiraniMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
