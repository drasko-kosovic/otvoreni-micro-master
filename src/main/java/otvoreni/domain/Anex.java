package otvoreni.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Anex.
 */
@Entity
@Table(name = "anex")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Anex implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sifra_postupka")
    private Integer sifra_postupka;

    @Column(name = "sifra_ponude")
    private Integer sifra_ponude;

    @Column(name = "atc")
    private String atc;

    @Column(name = "inn")
    private String inn;

    @Column(name = "zasticeni_naziv")
    private String zasticeni_naziv;

    @Column(name = "farmaceutski_oblik_lijeka")
    private String farmaceutski_oblik_lijeka;

    @Column(name = "jacina_lijeka")
    private String jacina_lijeka;

    @Column(name = "pakovanje")
    private String pakovanje;

    @Column(name = "trazena_kolicina")
    private Integer trazena_kolicina;

    @Column(name = "procijenjena_vrijednost")
    private Double procijenjena_vrijednost;

    @Column(name = "rok_isporuke")
    private Integer rok_isporuke;

    @Column(name = "naziv_ponudjaca")
    private String naziv_ponudjaca;

    @Column(name = "naziv_proizvodjaca")
    private String naziv_proizvodjaca;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Anex id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getSifra_postupka() {
        return this.sifra_postupka;
    }

    public Anex sifra_postupka(Integer sifra_postupka) {
        this.sifra_postupka = sifra_postupka;
        return this;
    }

    public void setSifra_postupka(Integer sifra_postupka) {
        this.sifra_postupka = sifra_postupka;
    }

    public Integer getSifra_ponude() {
        return this.sifra_ponude;
    }

    public Anex sifra_ponude(Integer sifra_ponude) {
        this.sifra_ponude = sifra_ponude;
        return this;
    }

    public void setSifra_ponude(Integer sifra_ponude) {
        this.sifra_ponude = sifra_ponude;
    }

    public String getAtc() {
        return this.atc;
    }

    public Anex atc(String atc) {
        this.atc = atc;
        return this;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public String getInn() {
        return this.inn;
    }

    public Anex inn(String inn) {
        this.inn = inn;
        return this;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getZasticeni_naziv() {
        return this.zasticeni_naziv;
    }

    public Anex zasticeni_naziv(String zasticeni_naziv) {
        this.zasticeni_naziv = zasticeni_naziv;
        return this;
    }

    public void setZasticeni_naziv(String zasticeni_naziv) {
        this.zasticeni_naziv = zasticeni_naziv;
    }

    public String getFarmaceutski_oblik_lijeka() {
        return this.farmaceutski_oblik_lijeka;
    }

    public Anex farmaceutski_oblik_lijeka(String farmaceutski_oblik_lijeka) {
        this.farmaceutski_oblik_lijeka = farmaceutski_oblik_lijeka;
        return this;
    }

    public void setFarmaceutski_oblik_lijeka(String farmaceutski_oblik_lijeka) {
        this.farmaceutski_oblik_lijeka = farmaceutski_oblik_lijeka;
    }

    public String getJacina_lijeka() {
        return this.jacina_lijeka;
    }

    public Anex jacina_lijeka(String jacina_lijeka) {
        this.jacina_lijeka = jacina_lijeka;
        return this;
    }

    public void setJacina_lijeka(String jacina_lijeka) {
        this.jacina_lijeka = jacina_lijeka;
    }

    public String getPakovanje() {
        return this.pakovanje;
    }

    public Anex pakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
        return this;
    }

    public void setPakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
    }

    public Integer getTrazena_kolicina() {
        return this.trazena_kolicina;
    }

    public Anex trazena_kolicina(Integer trazena_kolicina) {
        this.trazena_kolicina = trazena_kolicina;
        return this;
    }

    public void setTrazena_kolicina(Integer trazena_kolicina) {
        this.trazena_kolicina = trazena_kolicina;
    }

    public Double getProcijenjena_vrijednost() {
        return this.procijenjena_vrijednost;
    }

    public Anex procijenjena_vrijednost(Double procijenjena_vrijednost) {
        this.procijenjena_vrijednost = procijenjena_vrijednost;
        return this;
    }

    public void setProcijenjena_vrijednost(Double procijenjena_vrijednost) {
        this.procijenjena_vrijednost = procijenjena_vrijednost;
    }

    public Integer getRok_isporuke() {
        return this.rok_isporuke;
    }

    public Anex rok_isporuke(Integer rok_isporuke) {
        this.rok_isporuke = rok_isporuke;
        return this;
    }

    public void setRok_isporuke(Integer rok_isporuke) {
        this.rok_isporuke = rok_isporuke;
    }

    public String getNaziv_ponudjaca() {
        return this.naziv_ponudjaca;
    }

    public Anex naziv_ponudjaca(String naziv_ponudjaca) {
        this.naziv_ponudjaca = naziv_ponudjaca;
        return this;
    }

    public void setNaziv_ponudjaca(String naziv_ponudjaca) {
        this.naziv_ponudjaca = naziv_ponudjaca;
    }

    public String getNaziv_proizvodjaca() {
        return this.naziv_proizvodjaca;
    }

    public Anex naziv_proizvodjaca(String naziv_proizvodjaca) {
        this.naziv_proizvodjaca = naziv_proizvodjaca;
        return this;
    }

    public void setNaziv_proizvodjaca(String naziv_proizvodjaca) {
        this.naziv_proizvodjaca = naziv_proizvodjaca;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Anex)) {
            return false;
        }
        return id != null && id.equals(((Anex) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Anex{" +
            "id=" + getId() +
            ", sifra_postupka=" + getSifra_postupka() +
            ", sifra_ponude=" + getSifra_ponude() +
            ", atc='" + getAtc() + "'" +
            ", inn='" + getInn() + "'" +
            ", zasticeni_naziv='" + getZasticeni_naziv() + "'" +
            ", farmaceutski_oblik_lijeka='" + getFarmaceutski_oblik_lijeka() + "'" +
            ", jacina_lijeka='" + getJacina_lijeka() + "'" +
            ", pakovanje='" + getPakovanje() + "'" +
            ", trazena_kolicina=" + getTrazena_kolicina() +
            ", procijenjena_vrijednost=" + getProcijenjena_vrijednost() +
            ", rok_isporuke=" + getRok_isporuke() +
            ", naziv_ponudjaca='" + getNaziv_ponudjaca() + "'" +
            ", naziv_proizvodjaca='" + getNaziv_proizvodjaca() + "'" +
            "}";
    }
}
