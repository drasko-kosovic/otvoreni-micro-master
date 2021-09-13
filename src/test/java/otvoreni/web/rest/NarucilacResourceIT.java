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
import otvoreni.domain.Narucilac;
import otvoreni.repository.NarucilacRepository;

/**
 * Integration tests for the {@link NarucilacResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NarucilacResourceIT {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_RACUN = "AAAAAAAAAA";
    private static final String UPDATED_RACUN = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFON = "AAAAAAAAAA";
    private static final String UPDATED_TELEFON = "BBBBBBBBBB";

    private static final String DEFAULT_PIB = "AAAAAAAAAA";
    private static final String UPDATED_PIB = "BBBBBBBBBB";

    private static final String DEFAULT_PDV = "AAAAAAAAAA";
    private static final String UPDATED_PDV = "BBBBBBBBBB";

    private static final String DEFAULT_ODGOVORNO_LICE_NARUCIOCA = "AAAAAAAAAA";
    private static final String UPDATED_ODGOVORNO_LICE_NARUCIOCA = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESA = "AAAAAAAAAA";
    private static final String UPDATED_ADRESA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/narucilacs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NarucilacRepository narucilacRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNarucilacMockMvc;

    private Narucilac narucilac;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Narucilac createEntity(EntityManager em) {
        Narucilac narucilac = new Narucilac()
            .naziv(DEFAULT_NAZIV)
            .racun(DEFAULT_RACUN)
            .telefon(DEFAULT_TELEFON)
            .pib(DEFAULT_PIB)
            .pdv(DEFAULT_PDV)
            .odgovornoLiceNarucioca(DEFAULT_ODGOVORNO_LICE_NARUCIOCA)
            .email(DEFAULT_EMAIL)
            .adresa(DEFAULT_ADRESA);
        return narucilac;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Narucilac createUpdatedEntity(EntityManager em) {
        Narucilac narucilac = new Narucilac()
            .naziv(UPDATED_NAZIV)
            .racun(UPDATED_RACUN)
            .telefon(UPDATED_TELEFON)
            .pib(UPDATED_PIB)
            .pdv(UPDATED_PDV)
            .odgovornoLiceNarucioca(UPDATED_ODGOVORNO_LICE_NARUCIOCA)
            .email(UPDATED_EMAIL)
            .adresa(UPDATED_ADRESA);
        return narucilac;
    }

    @BeforeEach
    public void initTest() {
        narucilac = createEntity(em);
    }

    @Test
    @Transactional
    void createNarucilac() throws Exception {
        int databaseSizeBeforeCreate = narucilacRepository.findAll().size();
        // Create the Narucilac
        restNarucilacMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(narucilac)))
            .andExpect(status().isCreated());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeCreate + 1);
        Narucilac testNarucilac = narucilacList.get(narucilacList.size() - 1);
        assertThat(testNarucilac.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testNarucilac.getRacun()).isEqualTo(DEFAULT_RACUN);
        assertThat(testNarucilac.getTelefon()).isEqualTo(DEFAULT_TELEFON);
        assertThat(testNarucilac.getPib()).isEqualTo(DEFAULT_PIB);
        assertThat(testNarucilac.getPdv()).isEqualTo(DEFAULT_PDV);
        assertThat(testNarucilac.getOdgovornoLiceNarucioca()).isEqualTo(DEFAULT_ODGOVORNO_LICE_NARUCIOCA);
        assertThat(testNarucilac.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testNarucilac.getAdresa()).isEqualTo(DEFAULT_ADRESA);
    }

    @Test
    @Transactional
    void createNarucilacWithExistingId() throws Exception {
        // Create the Narucilac with an existing ID
        narucilac.setId(1L);

        int databaseSizeBeforeCreate = narucilacRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNarucilacMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(narucilac)))
            .andExpect(status().isBadRequest());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNarucilacs() throws Exception {
        // Initialize the database
        narucilacRepository.saveAndFlush(narucilac);

        // Get all the narucilacList
        restNarucilacMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(narucilac.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV)))
            .andExpect(jsonPath("$.[*].racun").value(hasItem(DEFAULT_RACUN)))
            .andExpect(jsonPath("$.[*].telefon").value(hasItem(DEFAULT_TELEFON)))
            .andExpect(jsonPath("$.[*].pib").value(hasItem(DEFAULT_PIB)))
            .andExpect(jsonPath("$.[*].pdv").value(hasItem(DEFAULT_PDV)))
            .andExpect(jsonPath("$.[*].odgovornoLiceNarucioca").value(hasItem(DEFAULT_ODGOVORNO_LICE_NARUCIOCA)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].adresa").value(hasItem(DEFAULT_ADRESA)));
    }

    @Test
    @Transactional
    void getNarucilac() throws Exception {
        // Initialize the database
        narucilacRepository.saveAndFlush(narucilac);

        // Get the narucilac
        restNarucilacMockMvc
            .perform(get(ENTITY_API_URL_ID, narucilac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(narucilac.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV))
            .andExpect(jsonPath("$.racun").value(DEFAULT_RACUN))
            .andExpect(jsonPath("$.telefon").value(DEFAULT_TELEFON))
            .andExpect(jsonPath("$.pib").value(DEFAULT_PIB))
            .andExpect(jsonPath("$.pdv").value(DEFAULT_PDV))
            .andExpect(jsonPath("$.odgovornoLiceNarucioca").value(DEFAULT_ODGOVORNO_LICE_NARUCIOCA))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.adresa").value(DEFAULT_ADRESA));
    }

    @Test
    @Transactional
    void getNonExistingNarucilac() throws Exception {
        // Get the narucilac
        restNarucilacMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNarucilac() throws Exception {
        // Initialize the database
        narucilacRepository.saveAndFlush(narucilac);

        int databaseSizeBeforeUpdate = narucilacRepository.findAll().size();

        // Update the narucilac
        Narucilac updatedNarucilac = narucilacRepository.findById(narucilac.getId()).get();
        // Disconnect from session so that the updates on updatedNarucilac are not directly saved in db
        em.detach(updatedNarucilac);
        updatedNarucilac
            .naziv(UPDATED_NAZIV)
            .racun(UPDATED_RACUN)
            .telefon(UPDATED_TELEFON)
            .pib(UPDATED_PIB)
            .pdv(UPDATED_PDV)
            .odgovornoLiceNarucioca(UPDATED_ODGOVORNO_LICE_NARUCIOCA)
            .email(UPDATED_EMAIL)
            .adresa(UPDATED_ADRESA);

        restNarucilacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedNarucilac.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedNarucilac))
            )
            .andExpect(status().isOk());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeUpdate);
        Narucilac testNarucilac = narucilacList.get(narucilacList.size() - 1);
        assertThat(testNarucilac.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testNarucilac.getRacun()).isEqualTo(UPDATED_RACUN);
        assertThat(testNarucilac.getTelefon()).isEqualTo(UPDATED_TELEFON);
        assertThat(testNarucilac.getPib()).isEqualTo(UPDATED_PIB);
        assertThat(testNarucilac.getPdv()).isEqualTo(UPDATED_PDV);
        assertThat(testNarucilac.getOdgovornoLiceNarucioca()).isEqualTo(UPDATED_ODGOVORNO_LICE_NARUCIOCA);
        assertThat(testNarucilac.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testNarucilac.getAdresa()).isEqualTo(UPDATED_ADRESA);
    }

    @Test
    @Transactional
    void putNonExistingNarucilac() throws Exception {
        int databaseSizeBeforeUpdate = narucilacRepository.findAll().size();
        narucilac.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNarucilacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, narucilac.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(narucilac))
            )
            .andExpect(status().isBadRequest());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNarucilac() throws Exception {
        int databaseSizeBeforeUpdate = narucilacRepository.findAll().size();
        narucilac.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNarucilacMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(narucilac))
            )
            .andExpect(status().isBadRequest());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNarucilac() throws Exception {
        int databaseSizeBeforeUpdate = narucilacRepository.findAll().size();
        narucilac.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNarucilacMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(narucilac)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNarucilacWithPatch() throws Exception {
        // Initialize the database
        narucilacRepository.saveAndFlush(narucilac);

        int databaseSizeBeforeUpdate = narucilacRepository.findAll().size();

        // Update the narucilac using partial update
        Narucilac partialUpdatedNarucilac = new Narucilac();
        partialUpdatedNarucilac.setId(narucilac.getId());

        partialUpdatedNarucilac
            .racun(UPDATED_RACUN)
            .pib(UPDATED_PIB)
            .pdv(UPDATED_PDV)
            .odgovornoLiceNarucioca(UPDATED_ODGOVORNO_LICE_NARUCIOCA)
            .email(UPDATED_EMAIL)
            .adresa(UPDATED_ADRESA);

        restNarucilacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNarucilac.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNarucilac))
            )
            .andExpect(status().isOk());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeUpdate);
        Narucilac testNarucilac = narucilacList.get(narucilacList.size() - 1);
        assertThat(testNarucilac.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testNarucilac.getRacun()).isEqualTo(UPDATED_RACUN);
        assertThat(testNarucilac.getTelefon()).isEqualTo(DEFAULT_TELEFON);
        assertThat(testNarucilac.getPib()).isEqualTo(UPDATED_PIB);
        assertThat(testNarucilac.getPdv()).isEqualTo(UPDATED_PDV);
        assertThat(testNarucilac.getOdgovornoLiceNarucioca()).isEqualTo(UPDATED_ODGOVORNO_LICE_NARUCIOCA);
        assertThat(testNarucilac.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testNarucilac.getAdresa()).isEqualTo(UPDATED_ADRESA);
    }

    @Test
    @Transactional
    void fullUpdateNarucilacWithPatch() throws Exception {
        // Initialize the database
        narucilacRepository.saveAndFlush(narucilac);

        int databaseSizeBeforeUpdate = narucilacRepository.findAll().size();

        // Update the narucilac using partial update
        Narucilac partialUpdatedNarucilac = new Narucilac();
        partialUpdatedNarucilac.setId(narucilac.getId());

        partialUpdatedNarucilac
            .naziv(UPDATED_NAZIV)
            .racun(UPDATED_RACUN)
            .telefon(UPDATED_TELEFON)
            .pib(UPDATED_PIB)
            .pdv(UPDATED_PDV)
            .odgovornoLiceNarucioca(UPDATED_ODGOVORNO_LICE_NARUCIOCA)
            .email(UPDATED_EMAIL)
            .adresa(UPDATED_ADRESA);

        restNarucilacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNarucilac.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNarucilac))
            )
            .andExpect(status().isOk());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeUpdate);
        Narucilac testNarucilac = narucilacList.get(narucilacList.size() - 1);
        assertThat(testNarucilac.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testNarucilac.getRacun()).isEqualTo(UPDATED_RACUN);
        assertThat(testNarucilac.getTelefon()).isEqualTo(UPDATED_TELEFON);
        assertThat(testNarucilac.getPib()).isEqualTo(UPDATED_PIB);
        assertThat(testNarucilac.getPdv()).isEqualTo(UPDATED_PDV);
        assertThat(testNarucilac.getOdgovornoLiceNarucioca()).isEqualTo(UPDATED_ODGOVORNO_LICE_NARUCIOCA);
        assertThat(testNarucilac.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testNarucilac.getAdresa()).isEqualTo(UPDATED_ADRESA);
    }

    @Test
    @Transactional
    void patchNonExistingNarucilac() throws Exception {
        int databaseSizeBeforeUpdate = narucilacRepository.findAll().size();
        narucilac.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNarucilacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, narucilac.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(narucilac))
            )
            .andExpect(status().isBadRequest());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNarucilac() throws Exception {
        int databaseSizeBeforeUpdate = narucilacRepository.findAll().size();
        narucilac.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNarucilacMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(narucilac))
            )
            .andExpect(status().isBadRequest());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNarucilac() throws Exception {
        int databaseSizeBeforeUpdate = narucilacRepository.findAll().size();
        narucilac.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNarucilacMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(narucilac))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Narucilac in the database
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNarucilac() throws Exception {
        // Initialize the database
        narucilacRepository.saveAndFlush(narucilac);

        int databaseSizeBeforeDelete = narucilacRepository.findAll().size();

        // Delete the narucilac
        restNarucilacMockMvc
            .perform(delete(ENTITY_API_URL_ID, narucilac.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Narucilac> narucilacList = narucilacRepository.findAll();
        assertThat(narucilacList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
