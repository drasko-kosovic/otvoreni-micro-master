package otvoreni.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Narucilac.
 */
@Entity
@Table(name = "narucilac")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Narucilac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "racun")
    private String racun;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "pib")
    private String pib;

    @Column(name = "pdv")
    private String pdv;

    @Column(name = "odgovorno_lice_narucioca")
    private String odgovornoLiceNarucioca;

    @Column(name = "email")
    private String email;

    @Column(name = "adresa")
    private String adresa;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Narucilac id(Long id) {
        this.id = id;
        return this;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public Narucilac naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getRacun() {
        return this.racun;
    }

    public Narucilac racun(String racun) {
        this.racun = racun;
        return this;
    }

    public void setRacun(String racun) {
        this.racun = racun;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public Narucilac telefon(String telefon) {
        this.telefon = telefon;
        return this;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPib() {
        return this.pib;
    }

    public Narucilac pib(String pib) {
        this.pib = pib;
        return this;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getPdv() {
        return this.pdv;
    }

    public Narucilac pdv(String pdv) {
        this.pdv = pdv;
        return this;
    }

    public void setPdv(String pdv) {
        this.pdv = pdv;
    }

    public String getOdgovornoLiceNarucioca() {
        return this.odgovornoLiceNarucioca;
    }

    public Narucilac odgovornoLiceNarucioca(String odgovornoLiceNarucioca) {
        this.odgovornoLiceNarucioca = odgovornoLiceNarucioca;
        return this;
    }

    public void setOdgovornoLiceNarucioca(String odgovornoLiceNarucioca) {
        this.odgovornoLiceNarucioca = odgovornoLiceNarucioca;
    }

    public String getEmail() {
        return this.email;
    }

    public Narucilac email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return this.adresa;
    }

    public Narucilac adresa(String adresa) {
        this.adresa = adresa;
        return this;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Narucilac)) {
            return false;
        }
        return id != null && id.equals(((Narucilac) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Narucilac{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", racun='" + getRacun() + "'" +
            ", telefon='" + getTelefon() + "'" +
            ", pib='" + getPib() + "'" +
            ", pdv='" + getPdv() + "'" +
            ", odgovornoLiceNarucioca='" + getOdgovornoLiceNarucioca() + "'" +
            ", email='" + getEmail() + "'" +
            ", adresa='" + getAdresa() + "'" +
            "}";
    }
}
