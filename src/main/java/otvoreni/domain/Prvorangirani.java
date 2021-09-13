package otvoreni.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Prvorangirani.
 */
@Entity
@Table(name = "prvorangirani")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Prvorangirani implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sifra_postupka")
    private Integer sifraPostupka;

    @Column(name = "sifra_ponude")
    private Integer sifraPonude;

    @Column(name = "atc")
    private String atc;

    @Column(name = "inn")
    private String inn;

    @Column(name = "zasticeni_naziv")
    private String zasticeniNaziv;

    @Column(name = "farmaceutski_oblik_lijeka")
    private String farmaceutskiOblikLijeka;

    @Column(name = "jacina_lijeka")
    private String jacinaLijeka;

    @Column(name = "pakovanje")
    private String pakovanje;

    @Column(name = "trazena_kolicina")
    private Integer trazenaKolicina;

    @Column(name = "procijenjena_vrijednost")
    private Double procijenjenaVrijednost;

    @Column(name = "rok_isporuke")
    private Integer rokIsporuke;

    @Column(name = "naziv_proizvodjaca")
    private String nazivProizvodjaca;

    @Column(name = "naziv_ponudjaca")
    private String nazivPonudjaca;

    @Column(name = "bod_cijena")
    private Double bodCijena;

    @Column(name = "bod_rok")
    private Double bodRok;

    @Column(name = "bod_ukupno")
    private Double bodUkupno;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prvorangirani id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getSifraPostupka() {
        return this.sifraPostupka;
    }

    public Prvorangirani sifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
        return this;
    }

    public void setSifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
    }

    public Integer getSifraPonude() {
        return this.sifraPonude;
    }

    public Prvorangirani sifraPonude(Integer sifraPonude) {
        this.sifraPonude = sifraPonude;
        return this;
    }

    public void setSifraPonude(Integer sifraPonude) {
        this.sifraPonude = sifraPonude;
    }

    public String getAtc() {
        return this.atc;
    }

    public Prvorangirani atc(String atc) {
        this.atc = atc;
        return this;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public String getInn() {
        return this.inn;
    }

    public Prvorangirani inn(String inn) {
        this.inn = inn;
        return this;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getZasticeniNaziv() {
        return this.zasticeniNaziv;
    }

    public Prvorangirani zasticeniNaziv(String zasticeniNaziv) {
        this.zasticeniNaziv = zasticeniNaziv;
        return this;
    }

    public void setZasticeniNaziv(String zasticeniNaziv) {
        this.zasticeniNaziv = zasticeniNaziv;
    }

    public String getFarmaceutskiOblikLijeka() {
        return this.farmaceutskiOblikLijeka;
    }

    public Prvorangirani farmaceutskiOblikLijeka(String farmaceutskiOblikLijeka) {
        this.farmaceutskiOblikLijeka = farmaceutskiOblikLijeka;
        return this;
    }

    public void setFarmaceutskiOblikLijeka(String farmaceutskiOblikLijeka) {
        this.farmaceutskiOblikLijeka = farmaceutskiOblikLijeka;
    }

    public String getJacinaLijeka() {
        return this.jacinaLijeka;
    }

    public Prvorangirani jacinaLijeka(String jacinaLijeka) {
        this.jacinaLijeka = jacinaLijeka;
        return this;
    }

    public void setJacinaLijeka(String jacinaLijeka) {
        this.jacinaLijeka = jacinaLijeka;
    }

    public String getPakovanje() {
        return this.pakovanje;
    }

    public Prvorangirani pakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
        return this;
    }

    public void setPakovanje(String pakovanje) {
        this.pakovanje = pakovanje;
    }

    public Integer getTrazenaKolicina() {
        return this.trazenaKolicina;
    }

    public Prvorangirani trazenaKolicina(Integer trazenaKolicina) {
        this.trazenaKolicina = trazenaKolicina;
        return this;
    }

    public void setTrazenaKolicina(Integer trazenaKolicina) {
        this.trazenaKolicina = trazenaKolicina;
    }

    public Double getProcijenjenaVrijednost() {
        return this.procijenjenaVrijednost;
    }

    public Prvorangirani procijenjenaVrijednost(Double procijenjenaVrijednost) {
        this.procijenjenaVrijednost = procijenjenaVrijednost;
        return this;
    }

    public void setProcijenjenaVrijednost(Double procijenjenaVrijednost) {
        this.procijenjenaVrijednost = procijenjenaVrijednost;
    }

    public Integer getRokIsporuke() {
        return this.rokIsporuke;
    }

    public Prvorangirani rokIsporuke(Integer rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
        return this;
    }

    public void setRokIsporuke(Integer rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    public String getNazivProizvodjaca() {
        return this.nazivProizvodjaca;
    }

    public Prvorangirani nazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
        return this;
    }

    public void setNazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public String getNazivPonudjaca() {
        return this.nazivPonudjaca;
    }

    public Prvorangirani nazivPonudjaca(String nazivPonudjaca) {
        this.nazivPonudjaca = nazivPonudjaca;
        return this;
    }

    public void setNazivPonudjaca(String nazivPonudjaca) {
        this.nazivPonudjaca = nazivPonudjaca;
    }

    public Double getBodCijena() {
        return this.bodCijena;
    }

    public Prvorangirani bodCijena(Double bodCijena) {
        this.bodCijena = bodCijena;
        return this;
    }

    public void setBodCijena(Double bodCijena) {
        this.bodCijena = bodCijena;
    }

    public Double getBodRok() {
        return this.bodRok;
    }

    public Prvorangirani bodRok(Double bodRok) {
        this.bodRok = bodRok;
        return this;
    }

    public void setBodRok(Double bodRok) {
        this.bodRok = bodRok;
    }

    public Double getBodUkupno() {
        return this.bodUkupno;
    }

    public Prvorangirani bodUkupno(Double bodUkupno) {
        this.bodUkupno = bodUkupno;
        return this;
    }

    public void setBodUkupno(Double bodUkupno) {
        this.bodUkupno = bodUkupno;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prvorangirani)) {
            return false;
        }
        return id != null && id.equals(((Prvorangirani) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Prvorangirani{" +
            "id=" + getId() +
            ", sifraPostupka=" + getSifraPostupka() +
            ", sifraPonude=" + getSifraPonude() +
            ", atc='" + getAtc() + "'" +
            ", inn='" + getInn() + "'" +
            ", zasticeniNaziv='" + getZasticeniNaziv() + "'" +
            ", farmaceutskiOblikLijeka='" + getFarmaceutskiOblikLijeka() + "'" +
            ", jacinaLijeka='" + getJacinaLijeka() + "'" +
            ", pakovanje='" + getPakovanje() + "'" +
            ", trazenaKolicina=" + getTrazenaKolicina() +
            ", procijenjenaVrijednost=" + getProcijenjenaVrijednost() +
            ", rokIsporuke=" + getRokIsporuke() +
            ", nazivProizvodjaca='" + getNazivProizvodjaca() + "'" +
            ", nazivPonudjaca='" + getNazivPonudjaca() + "'" +
            ", bodCijena=" + getBodCijena() +
            ", bodRok=" + getBodRok() +
            ", bodUkupno=" + getBodUkupno() +
            "}";
    }
}
