package otvoreni.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Ponude.
 */
@Entity
@Table(name = "ponude")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ponude implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "sifra_ponude", nullable = false)
    private Integer sifraPonude;

    @NotNull
    @Column(name = "sifra_postupka", nullable = false)
    private Integer sifraPostupka;

    @NotNull
    @Column(name = "broj_partije", nullable = false)
    private Integer brojPartije;

    @Column(name = "naziv_proizvodjaca")
    private String nazivProizvodjaca;

    @Column(name = "zasticeni_naziv")
    private String zasticeniNaziv;

    @Column(name = "ponudjena_vrijednost")
    private Double ponudjenaVrijednost;

    @Column(name = "rok_isporuke")
    private Integer rokIsporuke;

    @Column(name = "datum_ponude")
    private LocalDate datumPonude;

    @Column(name = "sifra_ponudjaca")
    private Integer sifraPonudjaca;

    @Column(name = "selected")
    private Boolean selected;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ponude id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getSifraPonude() {
        return this.sifraPonude;
    }

    public Ponude sifraPonude(Integer sifraPonude) {
        this.sifraPonude = sifraPonude;
        return this;
    }

    public void setSifraPonude(Integer sifraPonude) {
        this.sifraPonude = sifraPonude;
    }

    public Integer getSifraPostupka() {
        return this.sifraPostupka;
    }

    public Ponude sifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
        return this;
    }

    public void setSifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
    }

    public Integer getBrojPartije() {
        return this.brojPartije;
    }

    public Ponude brojPartije(Integer brojPartije) {
        this.brojPartije = brojPartije;
        return this;
    }

    public void setBrojPartije(Integer brojPartije) {
        this.brojPartije = brojPartije;
    }

    public String getNazivProizvodjaca() {
        return this.nazivProizvodjaca;
    }

    public Ponude nazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
        return this;
    }

    public void setNazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public String getZasticeniNaziv() {
        return this.zasticeniNaziv;
    }

    public Ponude zasticeniNaziv(String zasticeniNaziv) {
        this.zasticeniNaziv = zasticeniNaziv;
        return this;
    }

    public void setZasticeniNaziv(String zasticeniNaziv) {
        this.zasticeniNaziv = zasticeniNaziv;
    }

    public Double getPonudjenaVrijednost() {
        return this.ponudjenaVrijednost;
    }

    public Ponude ponudjenaVrijednost(Double ponudjenaVrijednost) {
        this.ponudjenaVrijednost = ponudjenaVrijednost;
        return this;
    }

    public void setPonudjenaVrijednost(Double ponudjenaVrijednost) {
        this.ponudjenaVrijednost = ponudjenaVrijednost;
    }

    public Integer getRokIsporuke() {
        return this.rokIsporuke;
    }

    public Ponude rokIsporuke(Integer rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
        return this;
    }

    public void setRokIsporuke(Integer rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    public LocalDate getDatumPonude() {
        return this.datumPonude;
    }

    public Ponude datumPonude(LocalDate datumPonude) {
        this.datumPonude = datumPonude;
        return this;
    }

    public void setDatumPonude(LocalDate datumPonude) {
        this.datumPonude = datumPonude;
    }

    public Integer getSifraPonudjaca() {
        return this.sifraPonudjaca;
    }

    public Ponude sifraPonudjaca(Integer sifraPonudjaca) {
        this.sifraPonudjaca = sifraPonudjaca;
        return this;
    }

    public void setSifraPonudjaca(Integer sifraPonudjaca) {
        this.sifraPonudjaca = sifraPonudjaca;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public Ponude selected(Boolean selected) {
        this.selected = selected;
        return this;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ponude)) {
            return false;
        }
        return id != null && id.equals(((Ponude) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ponude{" +
            "id=" + getId() +
            ", sifraPonude=" + getSifraPonude() +
            ", sifraPostupka=" + getSifraPostupka() +
            ", brojPartije=" + getBrojPartije() +
            ", nazivProizvodjaca='" + getNazivProizvodjaca() + "'" +
            ", zasticeniNaziv='" + getZasticeniNaziv() + "'" +
            ", ponudjenaVrijednost=" + getPonudjenaVrijednost() +
            ", rokIsporuke=" + getRokIsporuke() +
            ", datumPonude='" + getDatumPonude() + "'" +
            ", sifraPonudjaca=" + getSifraPonudjaca() +
            ", selected='" + getSelected() + "'" +
            "}";
    }
}
