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
import otvoreni.domain.Anex;
import otvoreni.repository.AnexRepository;

/**
 * Integration tests for the {@link AnexResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnexResourceIT {

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

    private static final String DEFAULT_NAZIV_PONUDJACA = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_PONUDJACA = "BBBBBBBBBB";

    private static final String DEFAULT_NAZIV_PROIZVODJACA = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV_PROIZVODJACA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/anexes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnexRepository anexRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnexMockMvc;

    private Anex anex;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Anex createEntity(EntityManager em) {
        Anex anex = new Anex()
            .sifra_postupka(DEFAULT_SIFRA_POSTUPKA)
            .sifra_ponude(DEFAULT_SIFRA_PONUDE)
            .atc(DEFAULT_ATC)
            .inn(DEFAULT_INN)
            .zasticeni_naziv(DEFAULT_ZASTICENI_NAZIV)
            .farmaceutski_oblik_lijeka(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacina_lijeka(DEFAULT_JACINA_LIJEKA)
            .pakovanje(DEFAULT_PAKOVANJE)
            .trazena_kolicina(DEFAULT_TRAZENA_KOLICINA)
            .procijenjena_vrijednost(DEFAULT_PROCIJENJENA_VRIJEDNOST)
            .rok_isporuke(DEFAULT_ROK_ISPORUKE)
            .naziv_ponudjaca(DEFAULT_NAZIV_PONUDJACA)
            .naziv_proizvodjaca(DEFAULT_NAZIV_PROIZVODJACA);
        return anex;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Anex createUpdatedEntity(EntityManager em) {
        Anex anex = new Anex()
            .sifra_postupka(UPDATED_SIFRA_POSTUPKA)
            .sifra_ponude(UPDATED_SIFRA_PONUDE)
            .atc(UPDATED_ATC)
            .inn(UPDATED_INN)
            .zasticeni_naziv(UPDATED_ZASTICENI_NAZIV)
            .farmaceutski_oblik_lijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacina_lijeka(UPDATED_JACINA_LIJEKA)
            .pakovanje(UPDATED_PAKOVANJE)
            .trazena_kolicina(UPDATED_TRAZENA_KOLICINA)
            .procijenjena_vrijednost(UPDATED_PROCIJENJENA_VRIJEDNOST)
            .rok_isporuke(UPDATED_ROK_ISPORUKE)
            .naziv_ponudjaca(UPDATED_NAZIV_PONUDJACA)
            .naziv_proizvodjaca(UPDATED_NAZIV_PROIZVODJACA);
        return anex;
    }

    @BeforeEach
    public void initTest() {
        anex = createEntity(em);
    }

    @Test
    @Transactional
    void createAnex() throws Exception {
        int databaseSizeBeforeCreate = anexRepository.findAll().size();
        // Create the Anex
        restAnexMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(anex)))
            .andExpect(status().isCreated());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeCreate + 1);
        Anex testAnex = anexList.get(anexList.size() - 1);
        assertThat(testAnex.getSifra_postupka()).isEqualTo(DEFAULT_SIFRA_POSTUPKA);
        assertThat(testAnex.getSifra_ponude()).isEqualTo(DEFAULT_SIFRA_PONUDE);
        assertThat(testAnex.getAtc()).isEqualTo(DEFAULT_ATC);
        assertThat(testAnex.getInn()).isEqualTo(DEFAULT_INN);
        assertThat(testAnex.getZasticeni_naziv()).isEqualTo(DEFAULT_ZASTICENI_NAZIV);
        assertThat(testAnex.getFarmaceutski_oblik_lijeka()).isEqualTo(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA);
        assertThat(testAnex.getJacina_lijeka()).isEqualTo(DEFAULT_JACINA_LIJEKA);
        assertThat(testAnex.getPakovanje()).isEqualTo(DEFAULT_PAKOVANJE);
        assertThat(testAnex.getTrazena_kolicina()).isEqualTo(DEFAULT_TRAZENA_KOLICINA);
        assertThat(testAnex.getProcijenjena_vrijednost()).isEqualTo(DEFAULT_PROCIJENJENA_VRIJEDNOST);
        assertThat(testAnex.getRok_isporuke()).isEqualTo(DEFAULT_ROK_ISPORUKE);
        assertThat(testAnex.getNaziv_ponudjaca()).isEqualTo(DEFAULT_NAZIV_PONUDJACA);
        assertThat(testAnex.getNaziv_proizvodjaca()).isEqualTo(DEFAULT_NAZIV_PROIZVODJACA);
    }

    @Test
    @Transactional
    void createAnexWithExistingId() throws Exception {
        // Create the Anex with an existing ID
        anex.setId(1L);

        int databaseSizeBeforeCreate = anexRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnexMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(anex)))
            .andExpect(status().isBadRequest());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAnexes() throws Exception {
        // Initialize the database
        anexRepository.saveAndFlush(anex);

        // Get all the anexList
        restAnexMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anex.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifra_postupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].sifra_ponude").value(hasItem(DEFAULT_SIFRA_PONUDE)))
            .andExpect(jsonPath("$.[*].atc").value(hasItem(DEFAULT_ATC)))
            .andExpect(jsonPath("$.[*].inn").value(hasItem(DEFAULT_INN)))
            .andExpect(jsonPath("$.[*].zasticeni_naziv").value(hasItem(DEFAULT_ZASTICENI_NAZIV)))
            .andExpect(jsonPath("$.[*].farmaceutski_oblik_lijeka").value(hasItem(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA)))
            .andExpect(jsonPath("$.[*].jacina_lijeka").value(hasItem(DEFAULT_JACINA_LIJEKA)))
            .andExpect(jsonPath("$.[*].pakovanje").value(hasItem(DEFAULT_PAKOVANJE)))
            .andExpect(jsonPath("$.[*].trazena_kolicina").value(hasItem(DEFAULT_TRAZENA_KOLICINA)))
            .andExpect(jsonPath("$.[*].procijenjena_vrijednost").value(hasItem(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].rok_isporuke").value(hasItem(DEFAULT_ROK_ISPORUKE)))
            .andExpect(jsonPath("$.[*].naziv_ponudjaca").value(hasItem(DEFAULT_NAZIV_PONUDJACA)))
            .andExpect(jsonPath("$.[*].naziv_proizvodjaca").value(hasItem(DEFAULT_NAZIV_PROIZVODJACA)));
    }

    @Test
    @Transactional
    void getAnex() throws Exception {
        // Initialize the database
        anexRepository.saveAndFlush(anex);

        // Get the anex
        restAnexMockMvc
            .perform(get(ENTITY_API_URL_ID, anex.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(anex.getId().intValue()))
            .andExpect(jsonPath("$.sifra_postupka").value(DEFAULT_SIFRA_POSTUPKA))
            .andExpect(jsonPath("$.sifra_ponude").value(DEFAULT_SIFRA_PONUDE))
            .andExpect(jsonPath("$.atc").value(DEFAULT_ATC))
            .andExpect(jsonPath("$.inn").value(DEFAULT_INN))
            .andExpect(jsonPath("$.zasticeni_naziv").value(DEFAULT_ZASTICENI_NAZIV))
            .andExpect(jsonPath("$.farmaceutski_oblik_lijeka").value(DEFAULT_FARMACEUTSKI_OBLIK_LIJEKA))
            .andExpect(jsonPath("$.jacina_lijeka").value(DEFAULT_JACINA_LIJEKA))
            .andExpect(jsonPath("$.pakovanje").value(DEFAULT_PAKOVANJE))
            .andExpect(jsonPath("$.trazena_kolicina").value(DEFAULT_TRAZENA_KOLICINA))
            .andExpect(jsonPath("$.procijenjena_vrijednost").value(DEFAULT_PROCIJENJENA_VRIJEDNOST.doubleValue()))
            .andExpect(jsonPath("$.rok_isporuke").value(DEFAULT_ROK_ISPORUKE))
            .andExpect(jsonPath("$.naziv_ponudjaca").value(DEFAULT_NAZIV_PONUDJACA))
            .andExpect(jsonPath("$.naziv_proizvodjaca").value(DEFAULT_NAZIV_PROIZVODJACA));
    }

    @Test
    @Transactional
    void getNonExistingAnex() throws Exception {
        // Get the anex
        restAnexMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAnex() throws Exception {
        // Initialize the database
        anexRepository.saveAndFlush(anex);

        int databaseSizeBeforeUpdate = anexRepository.findAll().size();

        // Update the anex
        Anex updatedAnex = anexRepository.findById(anex.getId()).get();
        // Disconnect from session so that the updates on updatedAnex are not directly saved in db
        em.detach(updatedAnex);
        updatedAnex
            .sifra_postupka(UPDATED_SIFRA_POSTUPKA)
            .sifra_ponude(UPDATED_SIFRA_PONUDE)
            .atc(UPDATED_ATC)
            .inn(UPDATED_INN)
            .zasticeni_naziv(UPDATED_ZASTICENI_NAZIV)
            .farmaceutski_oblik_lijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacina_lijeka(UPDATED_JACINA_LIJEKA)
            .pakovanje(UPDATED_PAKOVANJE)
            .trazena_kolicina(UPDATED_TRAZENA_KOLICINA)
            .procijenjena_vrijednost(UPDATED_PROCIJENJENA_VRIJEDNOST)
            .rok_isporuke(UPDATED_ROK_ISPORUKE)
            .naziv_ponudjaca(UPDATED_NAZIV_PONUDJACA)
            .naziv_proizvodjaca(UPDATED_NAZIV_PROIZVODJACA);

        restAnexMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAnex.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAnex))
            )
            .andExpect(status().isOk());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeUpdate);
        Anex testAnex = anexList.get(anexList.size() - 1);
        assertThat(testAnex.getSifra_postupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testAnex.getSifra_ponude()).isEqualTo(UPDATED_SIFRA_PONUDE);
        assertThat(testAnex.getAtc()).isEqualTo(UPDATED_ATC);
        assertThat(testAnex.getInn()).isEqualTo(UPDATED_INN);
        assertThat(testAnex.getZasticeni_naziv()).isEqualTo(UPDATED_ZASTICENI_NAZIV);
        assertThat(testAnex.getFarmaceutski_oblik_lijeka()).isEqualTo(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
        assertThat(testAnex.getJacina_lijeka()).isEqualTo(UPDATED_JACINA_LIJEKA);
        assertThat(testAnex.getPakovanje()).isEqualTo(UPDATED_PAKOVANJE);
        assertThat(testAnex.getTrazena_kolicina()).isEqualTo(UPDATED_TRAZENA_KOLICINA);
        assertThat(testAnex.getProcijenjena_vrijednost()).isEqualTo(UPDATED_PROCIJENJENA_VRIJEDNOST);
        assertThat(testAnex.getRok_isporuke()).isEqualTo(UPDATED_ROK_ISPORUKE);
        assertThat(testAnex.getNaziv_ponudjaca()).isEqualTo(UPDATED_NAZIV_PONUDJACA);
        assertThat(testAnex.getNaziv_proizvodjaca()).isEqualTo(UPDATED_NAZIV_PROIZVODJACA);
    }

    @Test
    @Transactional
    void putNonExistingAnex() throws Exception {
        int databaseSizeBeforeUpdate = anexRepository.findAll().size();
        anex.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnexMockMvc
            .perform(
                put(ENTITY_API_URL_ID, anex.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(anex))
            )
            .andExpect(status().isBadRequest());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnex() throws Exception {
        int databaseSizeBeforeUpdate = anexRepository.findAll().size();
        anex.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnexMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(anex))
            )
            .andExpect(status().isBadRequest());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnex() throws Exception {
        int databaseSizeBeforeUpdate = anexRepository.findAll().size();
        anex.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnexMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(anex)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnexWithPatch() throws Exception {
        // Initialize the database
        anexRepository.saveAndFlush(anex);

        int databaseSizeBeforeUpdate = anexRepository.findAll().size();

        // Update the anex using partial update
        Anex partialUpdatedAnex = new Anex();
        partialUpdatedAnex.setId(anex.getId());

        partialUpdatedAnex
            .sifra_postupka(UPDATED_SIFRA_POSTUPKA)
            .inn(UPDATED_INN)
            .zasticeni_naziv(UPDATED_ZASTICENI_NAZIV)
            .farmaceutski_oblik_lijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacina_lijeka(UPDATED_JACINA_LIJEKA)
            .pakovanje(UPDATED_PAKOVANJE)
            .procijenjena_vrijednost(UPDATED_PROCIJENJENA_VRIJEDNOST)
            .rok_isporuke(UPDATED_ROK_ISPORUKE)
            .naziv_ponudjaca(UPDATED_NAZIV_PONUDJACA);

        restAnexMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnex.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnex))
            )
            .andExpect(status().isOk());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeUpdate);
        Anex testAnex = anexList.get(anexList.size() - 1);
        assertThat(testAnex.getSifra_postupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testAnex.getSifra_ponude()).isEqualTo(DEFAULT_SIFRA_PONUDE);
        assertThat(testAnex.getAtc()).isEqualTo(DEFAULT_ATC);
        assertThat(testAnex.getInn()).isEqualTo(UPDATED_INN);
        assertThat(testAnex.getZasticeni_naziv()).isEqualTo(UPDATED_ZASTICENI_NAZIV);
        assertThat(testAnex.getFarmaceutski_oblik_lijeka()).isEqualTo(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
        assertThat(testAnex.getJacina_lijeka()).isEqualTo(UPDATED_JACINA_LIJEKA);
        assertThat(testAnex.getPakovanje()).isEqualTo(UPDATED_PAKOVANJE);
        assertThat(testAnex.getTrazena_kolicina()).isEqualTo(DEFAULT_TRAZENA_KOLICINA);
        assertThat(testAnex.getProcijenjena_vrijednost()).isEqualTo(UPDATED_PROCIJENJENA_VRIJEDNOST);
        assertThat(testAnex.getRok_isporuke()).isEqualTo(UPDATED_ROK_ISPORUKE);
        assertThat(testAnex.getNaziv_ponudjaca()).isEqualTo(UPDATED_NAZIV_PONUDJACA);
        assertThat(testAnex.getNaziv_proizvodjaca()).isEqualTo(DEFAULT_NAZIV_PROIZVODJACA);
    }

    @Test
    @Transactional
    void fullUpdateAnexWithPatch() throws Exception {
        // Initialize the database
        anexRepository.saveAndFlush(anex);

        int databaseSizeBeforeUpdate = anexRepository.findAll().size();

        // Update the anex using partial update
        Anex partialUpdatedAnex = new Anex();
        partialUpdatedAnex.setId(anex.getId());

        partialUpdatedAnex
            .sifra_postupka(UPDATED_SIFRA_POSTUPKA)
            .sifra_ponude(UPDATED_SIFRA_PONUDE)
            .atc(UPDATED_ATC)
            .inn(UPDATED_INN)
            .zasticeni_naziv(UPDATED_ZASTICENI_NAZIV)
            .farmaceutski_oblik_lijeka(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA)
            .jacina_lijeka(UPDATED_JACINA_LIJEKA)
            .pakovanje(UPDATED_PAKOVANJE)
            .trazena_kolicina(UPDATED_TRAZENA_KOLICINA)
            .procijenjena_vrijednost(UPDATED_PROCIJENJENA_VRIJEDNOST)
            .rok_isporuke(UPDATED_ROK_ISPORUKE)
            .naziv_ponudjaca(UPDATED_NAZIV_PONUDJACA)
            .naziv_proizvodjaca(UPDATED_NAZIV_PROIZVODJACA);

        restAnexMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnex.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnex))
            )
            .andExpect(status().isOk());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeUpdate);
        Anex testAnex = anexList.get(anexList.size() - 1);
        assertThat(testAnex.getSifra_postupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testAnex.getSifra_ponude()).isEqualTo(UPDATED_SIFRA_PONUDE);
        assertThat(testAnex.getAtc()).isEqualTo(UPDATED_ATC);
        assertThat(testAnex.getInn()).isEqualTo(UPDATED_INN);
        assertThat(testAnex.getZasticeni_naziv()).isEqualTo(UPDATED_ZASTICENI_NAZIV);
        assertThat(testAnex.getFarmaceutski_oblik_lijeka()).isEqualTo(UPDATED_FARMACEUTSKI_OBLIK_LIJEKA);
        assertThat(testAnex.getJacina_lijeka()).isEqualTo(UPDATED_JACINA_LIJEKA);
        assertThat(testAnex.getPakovanje()).isEqualTo(UPDATED_PAKOVANJE);
        assertThat(testAnex.getTrazena_kolicina()).isEqualTo(UPDATED_TRAZENA_KOLICINA);
        assertThat(testAnex.getProcijenjena_vrijednost()).isEqualTo(UPDATED_PROCIJENJENA_VRIJEDNOST);
        assertThat(testAnex.getRok_isporuke()).isEqualTo(UPDATED_ROK_ISPORUKE);
        assertThat(testAnex.getNaziv_ponudjaca()).isEqualTo(UPDATED_NAZIV_PONUDJACA);
        assertThat(testAnex.getNaziv_proizvodjaca()).isEqualTo(UPDATED_NAZIV_PROIZVODJACA);
    }

    @Test
    @Transactional
    void patchNonExistingAnex() throws Exception {
        int databaseSizeBeforeUpdate = anexRepository.findAll().size();
        anex.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnexMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, anex.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(anex))
            )
            .andExpect(status().isBadRequest());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnex() throws Exception {
        int databaseSizeBeforeUpdate = anexRepository.findAll().size();
        anex.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnexMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(anex))
            )
            .andExpect(status().isBadRequest());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnex() throws Exception {
        int databaseSizeBeforeUpdate = anexRepository.findAll().size();
        anex.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnexMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(anex)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Anex in the database
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnex() throws Exception {
        // Initialize the database
        anexRepository.saveAndFlush(anex);

        int databaseSizeBeforeDelete = anexRepository.findAll().size();

        // Delete the anex
        restAnexMockMvc
            .perform(delete(ENTITY_API_URL_ID, anex.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Anex> anexList = anexRepository.findAll();
        assertThat(anexList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
