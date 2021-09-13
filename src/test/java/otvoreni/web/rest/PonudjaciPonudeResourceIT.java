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
import otvoreni.domain.PonudjaciPonude;
import otvoreni.repository.PonudjaciPonudeRepository;

/**
 * Integration tests for the {@link PonudjaciPonudeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PonudjaciPonudeResourceIT {

    private static final Integer DEFAULT_SIFRA_POSTUPKA = 1;
    private static final Integer UPDATED_SIFRA_POSTUPKA = 2;

    private static final Integer DEFAULT_SIFRA_PONUDE = 1;
    private static final Integer UPDATED_SIFRA_PONUDE = 2;

    private static final String DEFAULT_NAZIV_PONUDJACA = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_PONUDJACA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ponudjaci-ponudes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PonudjaciPonudeRepository ponudjaciPonudeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPonudjaciPonudeMockMvc;

    private PonudjaciPonude ponudjaciPonude;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PonudjaciPonude createEntity(EntityManager em) {
        PonudjaciPonude ponudjaciPonude = new PonudjaciPonude()
            .sifraPostupka(DEFAULT_SIFRA_POSTUPKA)
            .sifraPonude(DEFAULT_SIFRA_PONUDE)
            .nazivPonudjaca(DEFAULT_NAZIV_PONUDJACA);
        return ponudjaciPonude;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PonudjaciPonude createUpdatedEntity(EntityManager em) {
        PonudjaciPonude ponudjaciPonude = new PonudjaciPonude()
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .sifraPonude(UPDATED_SIFRA_PONUDE)
            .nazivPonudjaca(UPDATED_NAZIV_PONUDJACA);
        return ponudjaciPonude;
    }

    @BeforeEach
    public void initTest() {
        ponudjaciPonude = createEntity(em);
    }

    @Test
    @Transactional
    void getAllPonudjaciPonudes() throws Exception {
        // Initialize the database
        ponudjaciPonudeRepository.saveAndFlush(ponudjaciPonude);

        // Get all the ponudjaciPonudeList
        restPonudjaciPonudeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ponudjaciPonude.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraPostupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].sifraPonude").value(hasItem(DEFAULT_SIFRA_PONUDE)))
            .andExpect(jsonPath("$.[*].nazivPonudjaca").value(hasItem(DEFAULT_NAZIV_PONUDJACA)));
    }

    @Test
    @Transactional
    void getPonudjaciPonude() throws Exception {
        // Initialize the database
        ponudjaciPonudeRepository.saveAndFlush(ponudjaciPonude);

        // Get the ponudjaciPonude
        restPonudjaciPonudeMockMvc
            .perform(get(ENTITY_API_URL_ID, ponudjaciPonude.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ponudjaciPonude.getId().intValue()))
            .andExpect(jsonPath("$.sifraPostupka").value(DEFAULT_SIFRA_POSTUPKA))
            .andExpect(jsonPath("$.sifraPonude").value(DEFAULT_SIFRA_PONUDE))
            .andExpect(jsonPath("$.nazivPonudjaca").value(DEFAULT_NAZIV_PONUDJACA));
    }

    @Test
    @Transactional
    void getNonExistingPonudjaciPonude() throws Exception {
        // Get the ponudjaciPonude
        restPonudjaciPonudeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
