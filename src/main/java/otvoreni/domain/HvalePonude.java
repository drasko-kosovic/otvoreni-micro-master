package otvoreni.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HvalePonude.
 */
@Entity
@Table(name = "hvale_ponude")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HvalePonude implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sifra_postupka")
    private Integer sifraPostupka;

    @Column(name = "broj_partije")
    private Integer brojPartije;

    @Column(name = "inn")
    private String inn;

    @Column(name = "farmaceutski_oblik_lijeka")
    private String farmaceutskiOblikLijeka;

    @Column(name = "pakovanje")
    private String pakovanje;

    @Column(name = "trazena_kolicina")
    private Integer trazenaKolicina;

    @Column(name = "procijenjena_vrijednost")
    private Double procijenjenaVrijednost;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HvalePonude id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getSifraPostupka() {
        return this.sifraPostupka;
    }

    public HvalePonude sifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
        return this;
    }

    public void setSifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
    }

    public Integer getBrojPartije() {
        return this.brojPartije;
    }

    public HvalePonude brojPartije(Integer brojPartije) {
        this.brojPartije = brojPartije;
        return this;
    }

    public void setBrojPartije(Integer brojPartije) {
        this.brojPartije = brojPartije;
    }

    public String getInn() {
        return this.inn;
    }

    public HvalePonude inn(String inn) {
        this.inn = inn;
        return this;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getFarmaceutskiOblikLijeka() {
        return this.farmaceutskiOblikLijeka;
    }

    public HvalePonude farmaceutskiOblikLijeka(String farmaceutskiOblikLijeka) {
        this.farmaceutskiOblikLijeka = farmaceutskiOblikLijeka;
        return this;
    }

    public void setFarmaceutskiOblikLijeka(String farmaceutskiOblikLijeka) {
        this.farmaceutskiOblikLijeka = farmaceutskiOblikLijeka;
    }

    public String getPakovanje() {
        return this.pakovanje;
    }

    public HvalePonude pakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
        return this;
    }

    public void setPakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
    }

    public Integer getTrazenaKolicina() {
        return this.trazenaKolicina;
    }

    public HvalePonude trazenaKolicina(Integer trazenaKolicina) {
        this.trazenaKolicina = trazenaKolicina;
        return this;
    }

    public void setTrazenaKolicina(Integer trazenaKolicina) {
        this.trazenaKolicina = trazenaKolicina;
    }

    public Double getProcijenjenaVrijednost() {
        return this.procijenjenaVrijednost;
    }

    public HvalePonude procijenjenaVrijednost(Double procijenjenaVrijednost) {
        this.procijenjenaVrijednost = procijenjenaVrijednost;
        return this;
    }

    public void setProcijenjenaVrijednost(Double procijenjenaVrijednost) {
        this.procijenjenaVrijednost = procijenjenaVrijednost;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HvalePonude)) {
            return false;
        }
        return id != null && id.equals(((HvalePonude) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HvalePonude{" +
            "id=" + getId() +
            ", sifraPostupka=" + getSifraPostupka() +
            ", brojPartije=" + getBrojPartije() +
            ", inn='" + getInn() + "'" +
            ", farmaceutskiOblikLijeka='" + getFarmaceutskiOblikLijeka() + "'" +
            ", pakovanje='" + getPakovanje() + "'" +
            ", trazenaKolicina=" + getTrazenaKolicina() +
            ", procijenjenaVrijednost=" + getProcijenjenaVrijednost() +
            "}";
    }
}
